package sub5_create.sub1_coroutine_builder.launch

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * 코루틴을 생성하는 함수를 coroutine builder 라고 한다.
 *
 * 대표적인 coroutine builder:
 *      - runBlocking
 *      - launch
 *      - async
 *
 * 여기서는 coroutine builder 중 launch 에 대해 알아본다.
 *
 * launch
 *      새로운 코루틴(자식 코루틴)을 생성하는 builder
 *
 *      특징
 *          - CoroutineScope 의 확장 함수이다.
 *          - 반드시 CoroutineScope 안에서만 호출 가능하다.
 *          - 반환값은 없고 Job 을 반환한다. (작업 자체를 나타냄)
 *          - 비동기적으로 실행된다. (호출 즉시 실행되지 않을 수 있다)
 *
 *      실행 모델
 *          - launch 는 코루틴을 생성하고 "실행 가능한 상태"로 만든다.
 *          - 실제 실행 시점은 scheduler(event loop, dispatcher)에 의해 결정된다.
 *
 *          - 같은 스레드에서도 번갈아 실행될 수 있다. (interleaving)
 *          - Dispatcher 에 따라 다른 스레드에서 실행될 수도 있다.
 *
 *      runBlocking 과의 관계
 *          - runBlocking은 현재 스레드를 블로킹하면서 코루틴을 실행한다.
 *          - launch 는 runBlocking 내부에서 자식 코루틴을 생성한다.
 *          - runBlocking 은 모든 자식 코루틴이 끝날 때까지 종료되지 않는다.
 *
 */

fun main() = runBlocking {

    myPrint("runBlocking start")

    launch {
        myPrint("launch start")

        delay(1000)

        myPrint("launch end")
    }

    myPrint("runBlocking end")
}

/**
 * 실행 결과
 *      runBlocking start
 *      runBlocking end
 *      launch start
 *      (1초 후)
 *      launch end
 *
 * 설명
 *      - launch는 새로운 코루틴을 생성하지만 즉시 실행을 보장하지 않는다.
 *      - runBlocking 은 계속 실행되다가 "runBlocking end"를 먼저 출력한다.
 *      - 이후 scheduler 가 자식 코루틴을 실행한다.
 *      - 여기서 중요한 점은 "단일 스레드라서 동시성이 없다"가 아니라
 *        "단일 스레드라서 한 순간에 하나의 코루틴만 실행되며,
 *        이 예제처럼 기본 runBlocking 환경에서는 현재 코루틴이 suspend 하거나
 *        완료되어 실행권을 놓을 때 다른 코루틴으로 전환될 수 있다"는 것이다.
 *      - 따라서 부모 코루틴이 launch 후에도 suspend 없이 계속 달리면
 *        자식 코루틴은 실행 대기 상태로만 남아 있다가 부모 코드가 끝난 뒤 실행될 수 있다.
 *
 * 핵심
 *      - launch 는 "비동기 작업 실행용 builder"
 *      - 반환값 대신 Job 을 반환 (결과 없음)
 *      - 실행 순서는 scheduler 에 의해 결정된다.
 *
 * 주의
 *      - launch 내부 코드가 오래 실행되고 suspend 지점이 없으면
 *        같은 스레드의 다른 코루틴 실행을 막을 수 있다.
 */
