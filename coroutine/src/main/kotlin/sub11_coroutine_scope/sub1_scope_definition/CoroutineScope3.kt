package sub11_coroutine_scope.sub1_scope_definition

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import util.myPrint

suspend fun main() {

    myPrint("main start")

    val job = CoroutineScope(Dispatchers.Default).launch {
        myPrint("launch start")
        delay(1000L)
        myPrint("launch end")
    }

    job.join()

    myPrint("main end")
}
/**
 * main 함수에 suspend 붙여서 job.join() 을 호출할 수 있다.
 *
 * 출력 결과..
 *      [main] main start
 *      [DefaultDispatcher-worker-1 @coroutine#1] launch start
 *      [DefaultDispatcher-worker-1 @coroutine#1] launch end
 *      [DefaultDispatcher-worker-1 @coroutine#1] main end
 *
 * job.join 을 main thread 가 할텐데..
 * main end 로그 출력을 DefaultDispatcher-worker 스레드가 하는 이유..
 *      1. main 스레드가 join()을 호출하면 suspend (일시 중단)
 *      2. job이 완료되면 중단된 지점을 재개(resume) 시켜줌
 *      3. 이때 재개를 완료된 코루틴의 스레드가 담당
 *
 * [main thread]
 *     │
 *     ├── myPrint("main start")
 *     │
 *     ├── launch { ... }  ──────────────────► [DefaultDispatcher-worker-1]
 *     │                                             │
 *     ├── job.join()  ← suspend!                    ├── myPrint("launch start")
 *     │   (main thread 해방)                         ├── delay(1000L)
 *     │                                             ├── myPrint("launch end")
 *     │                                             │
 *     │   ◄── resume! (worker-1이 재개) ──────────────┘
 *     │
 *     └── myPrint("main end")  ← worker-1이 실행!
 */
