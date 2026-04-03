package sub5_resume_mechanism

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * 코루틴은 suspend 된 뒤 누가 다시 깨우는가?
 *
 * 핵심 답
 *      - 코루틴이 suspend 되면 현재 실행 상태가 Continuation 형태로 저장된다.
 *      - 그리고 "이 작업이 끝나면 이 Continuation 을 resume 하라"는 정보가 등록된다.
 *      - 나중에 대기하던 작업이 끝나면, 그 작업을 관리하던 주체가 resume 을 호출한다.
 *
 * 여기서 중요한 점
 *      - 코루틴이 스스로 깨어나는 것은 아니다.
 *      - suspend 시점에 "나중에 나를 깨워줄 주체"가 Continuation 을 넘겨받는다.
 *      - 이후 완료 시점에 그 주체가 resume() 또는 resumeWithException() 을 호출한다.
 *
 * resume 을 호출하는 주체의 예
 *      - delay()           -> 코루틴 라이브러리의 타이머 / scheduler
 *      - Deferred.await()  -> Deferred 를 완료시키는 쪽
 *      - Job.join()        -> 대상 Job 이 끝났다는 상태 전이 로직
 *      - non-blocking I/O  -> 비동기 라이브러리의 callback / event loop
 *
 * resume 이후에도 바로 실행된다고 단정하면 안 된다.
 *      - resume 은 "다시 실행 가능 상태로 만든다"에 가깝다.
 *      - 실제 이어서 실행되는 시점은 Dispatcher / event loop 의 스케줄링에 따라 결정된다.
 *
 * 아주 단순화한 delay 의 느낌
 *      suspend fun delay(...) {
 *          suspendCancellableCoroutine { cont ->
 *              timer.register(after = 1000) {
 *                  cont.resume(Unit)
 *              }
 *          }
 *      }
 *
 *      즉,
 *          1. 현재 코루틴은 suspend 된다.
 *          2. 스레드는 다른 작업을 할 수 있다.
 *          3. 타이머가 만료되면 등록된 cont.resume(Unit) 이 호출된다.
 *          4. 코루틴은 다시 실행 가능한 상태가 된다.
 *          5. 적절한 스레드에서 이어서 실행된다.
 *
 * delay 의 타이머는 누가 관리하는가?
 *      - delay 의 시간 경과를 감지하는 메커니즘도 결국 런타임 / 시스템의 실행 기반 위에서 동작한다.
 *      - 즉, 완전히 아무것도 없이 시간이 흐른 뒤 저절로 resume 되는 것은 아니다.
 *      - 내부적으로는 타이머, scheduler, event loop, worker thread 같은 메커니즘이 관여한다고 보면 된다.
 *      - 중요한 점은 "원래 이 코루틴을 실행하던 스레드"가 그 시간을 직접 기다리며 묶여 있지 않다는 것이다.
 */
fun main(): Unit = runBlocking {
    myPrint("runBlocking start")

    launch {
        myPrint("child start")
        routine()
        myPrint("child end")
    }

    myPrint("runBlocking end")
}

suspend fun routine() {
    myPrint("before delay")
    delay(1000L)
    myPrint("after delay")
}

/**
 * 실행 흐름
 *      1. runBlocking 이 현재 스레드에서 루트 코루틴을 실행한다.
 *      2. launch 로 자식 코루틴이 생성된다.
 *      3. 부모는 "runBlocking end" 를 출력한다.
 *      4. 자식 코루틴이 실행되어 "before delay" 를 출력한다.
 *      5. delay(1000L) 에서 자식 코루틴은 suspend 된다.
 *      6. 이 시점에 코루틴의 다음 실행 위치와 필요한 상태가 저장된다.
 *      7. 그리고 "1초 후 이 코루틴을 resume 하라"는 예약이 걸린다.
 *      8. 1초 후 타이머 / scheduler 가 resume 을 호출한다.
 *      9. 코루틴은 다시 실행 가능 상태가 되고, 적절한 스레드에서 재개된다.
 *      10. "after delay" 와 "child end" 를 출력하고 종료된다.
 *
 * 핵심
 *      - suspend 는 "잠시 멈추고 상태를 저장하는 것"
 *      - resume 은 "저장된 상태를 바탕으로 다시 실행 가능하게 만드는 것"
 *      - resume 을 호출하는 쪽은 보통 대기 작업의 완료를 알고 있는 외부 주체이다.
 *
 * 실제 I/O 에 대응시키면
 *      - non-blocking HTTP 요청을 보냈다고 가정해보자.
 *      - 요청을 보낸 뒤 코루틴은 suspend 될 수 있다.
 *      - 이후 네트워크 응답이 도착하면,
 *        HTTP client 내부 callback / event loop 가 해당 Continuation 을 resume 한다.
 *      - 그래서 "응답이 올 때까지 스레드가 멍하니 기다려야 하는 구조"가 아닐 수 있다.
 */
