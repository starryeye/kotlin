package sub2_coroutine_start

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import util.myPrint

/**
 * runBlocking
 *      public actual fun <T> runBlocking(context: CoroutineContext, block: suspend CoroutineScope.() -> T): T
 *      특징
 *          일반 함수(main 같은 진입점)에서 suspend 코드를 실행할 수 있게 해주는 코루틴 빌더
 *          CoroutineScope를 생성하고 block을 실행한다.
 *              block을 실행하는 루트 코루틴을 시작하며,
 *              현재 스레드를 "블로킹한 상태에서" 내부 event loop를 통해 코루틴 실행을 처리한다.
 *              파라미터 context는 expect 선언에서 기본값(EmptyCoroutineContext)을 가진다.
 *              즉, 현재 스레드는 외부로 반환되지 않고 묶여있지만 내부적으로는 해당 스레드에서 코루틴들이 계속 실행된다.
 *      코루틴 실행 모델
 *          runBlocking 은 기본적으로 단일 스레드에서 동작한다
 *          내부에 event loop를 가지고 코루틴을 스케줄링한다.
 *          별도의 dispatcher를 지정하지 않으면 같은 스레드에서 코루틴들이 번갈아 실행된다.
 *
 * launch
 *      public fun CoroutineScope.launch(
 *          context: CoroutineContext = EmptyCoroutineContext,
 *          start: CoroutineStart = CoroutineStart.DEFAULT,
 *          block: suspend CoroutineScope.() -> Unit
 *      ): Job
 *      특징
 *          CoroutineScope의 확장 함수 (코루틴 내부 또는 scope에서만 호출 가능)
 *          launch 블록을 실행하는 새로운 자식 코루틴을 생성한다.
 *          반환값 대신 Job을 반환하며, 실행 결과가 아닌 "작업 자체"를 수행하기 위한 용도이다.
 *          생성된 코루틴은 "즉시 실행 가능한 상태"가 되며,
 *          실제 실행 시점은 스케줄러(event loop)에 의해 결정된다.
 *
 * suspend fun
 *      suspend 함수나 코루틴 내부에서만 호출 가능
 *      다른 suspend fun 을 호출 할 수 있다.
 *      "항상 즉시 코루틴을 중단시키는 함수"가 아니라
 *      "필요한 경우 중단(suspend)될 수 있는 함수"라는 의미이다.
 *      즉, suspend fun 을 호출했다고 해서 반드시 중단되는 것은 아니다.
 *      suspend fun 함수 내부에 실제 suspend 지점(delay, yield, await 등)이 있으면
 *          그 지점에서 코루틴은 중단되고 스레드는 다른 작업을 하러 떠난다.
 *
 * yield
 *      현재 코루틴을 일시 중단(suspend)하고 실행을 양보한다.
 *      같은 스레드에서 대기 중인 다른 코루틴에게 실행 기회를 줄 수 있다.
 *
 * 아래 코드의 실행 흐름
 * 1. runBlocking → 현재 스레드에서 루트 코루틴 실행 시작
 * 2. "start" 출력
 * 3. launch → 자식 코루틴 생성 (event loop에 등록됨)
 * 4. 부모 코루틴에서 yield() → 실행 양보
 * 5. 자식 코루틴 실행
 *      → routine() 진입
 *      → num1, num2 초기화
 *      → yield() → 다시 양보
 * 6. 부모 코루틴 재개
 *      → "end" 출력
 * 7. runBlocking은 자식 코루틴이 끝날 때까지 종료되지 않음
 * 8. 자식 코루틴 재개
 *      → "total = 3" 출력
 * 9. 모든 코루틴 종료 후 runBlocking 종료
 *
 * 주의
 *      launch 는 코루틴을 즉시 실행하는 것이 아니라 실행 가능 상태로 등록한다.
 *      실행은 yield 같은 "양보 지점"에서 스케줄링된다.
 *      runBlocking은 단일 스레드 + event loop 기반이기 때문에 코루틴들은 같은 스레드에서 번갈아 실행된다.
 *      따라서 아래와 같은 순서가 보장됨
 *          start → end → total = 3
 *      불가능한 순서
 *          start → total = 3 → end
 *          routine 내부 yield() 때문에 중간에 반드시 양보가 발생함
 *
 * 참고
 *      yield 함수 두개다 없어도 순서는 동일한데.. 이유는..
 *          부모가 중간에 실행을 양보하지 않아서 부모의 작업을 모두 끝내고 자식의 작업을 수행한다.
 *          launch 는 자식 코루틴을 생성만 하고, 부모 코루틴이 즉시 계속 실행되기 때문
 *
 * 참고
 *      별도의 Dispatcher 를 지정하지 않았기 때문에
 *      스레드 관점에서 보면..
 *          모든 실행은 단일 스레드(main 스레드)에서 코루틴 스케줄이 일어난 것이다.
 *
 *
 * 루틴 vs 코루틴..
 * 루틴은 하나의 스레드 내에서 순차적으로 실행되며, 실행 스택을 벗어나면 다시 이어서 실행할 수 없다.
 *      한번 루틴 블록 밖으로 벗어나면 해당 실행 흐름과 지역 상태는 사라진다.
 *
 * 반면 코루틴은 하나의 스레드 내에서도 실행 도중 중단(suspend)되었다가, 이후 다시 이어서 실행(resume)될 수 있다.
 *      코루틴이 중단되더라도 실행 상태(지역 변수, 진행 위치 등)가 Continuation 형태(힙 객체)로 저장되기 때문에
 *      이후 동일한 지점부터 실행을 이어갈 수 있다.
 *
 */
fun main(): Unit = runBlocking {
    myPrint("start")
    launch {
        routine()
    }
    yield()
    myPrint("end")
}

suspend fun routine() {
    val num1 = 1
    val num2 = 2
    yield()
    myPrint("total = ${num1 + num2}")
}
