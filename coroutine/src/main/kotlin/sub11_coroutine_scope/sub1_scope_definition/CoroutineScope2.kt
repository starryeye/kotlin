package sub11_coroutine_scope.sub1_scope_definition

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import util.myPrint

fun main() {

    myPrint("main start")

    CoroutineScope(Dispatchers.Default).launch {
        myPrint("launch start")
        delay(1000L)
        myPrint("launch end")
    }

    Thread.sleep(2000L)

    myPrint("main end")
}
/**
 * CoroutineScope 으로 새로운 코루틴 영역을 생성했기 때문에 기존의 예제 처럼 runBlocking 이 필요없는 것이다.
 *
 * 참고
 *      runBlocking 에서는 runBlocking 의 코루틴이 종료될 때까지 block 되므로 Thread.sleep 이 필요 없었지만..
 *      이 예제에서는 Thread.sleep 을 하지 않으면
 *      Dispatchers.Default 에 의해 코루틴이 모두 실행을 마치기전에
 *      main thread 가 실행을 끝내고 프로세스 종료를 시키기 때문에 Thread.sleep 을 넣어줌
 *
 * 참고
 *      Dispatchers.Default 는 ForkJoinPool (데몬 스레드)
 */