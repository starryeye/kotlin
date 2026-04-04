package sub10_coroutine_context.sub1_context_definition

import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * CoroutineContext 1 - definition
 *
 *      CoroutineContext 는 코루틴이 실행될 때 함께 들고 다니는 설정 묶음이다.
 *
 *      쉽게 말하면
 *          - "어느 스레드 쪽에서 실행할지"
 *          - "누구의 자식 Job 인지"
 *          - "이 코루틴 이름은 무엇인지"
 *      같은 정보를 담고 있는 객체다.
 *
 *      코루틴은 단순히 코드 블록만 있는 것이 아니라,
 *      항상 어떤 context 와 함께 실행된다.
 *
 *      즉, 지금까지 따로따로 보였던 설정과 제어 정보가
 *      실제로는 CoroutineContext 라는 한 묶음 안에 들어 있다고 보면 된다.
 *
 *
 * CoroutineScope 와의 관계
 *      sub11 의 CoroutineScope 도 결국 coroutineContext 를 가진 인터페이스다.
 *      즉, CoroutineScope 의 핵심은 사실 "어떤 context 를 들고 있는가"이다.
 *
 * 이 예제의 목적
 *      - runBlocking 이 이미 하나의 coroutineContext 를 가진다는 점을 본다.
 *      - context 안에서 Job, Dispatcher, CoroutineName 을 꺼내는 법을 본다.
 */
fun main(): Unit = runBlocking(CoroutineName("root-runBlocking")) {

    myPrint("coroutineContext = $coroutineContext")
    myPrint("coroutineContext name = ${coroutineContext[CoroutineName]}")
    myPrint("coroutineContext job = ${coroutineContext[Job]}") // coroutineContext.job 으로도 접근가능
    myPrint("coroutineContext dispatcher = ${coroutineContext[ContinuationInterceptor]}")

}

/**
 * 실행 결과 예시
 *
 *      [main] coroutineContext = [CoroutineName(root-runBlocking), BlockingCoroutine{Active}@..., BlockingEventLoop@...]
 *      [main] coroutineContext name = CoroutineName(root-runBlocking)
 *      [main] coroutineContext job = BlockingCoroutine{Active}@...
 *      [main] coroutineContext dispatcher = BlockingEventLoop@...
 *
 */
