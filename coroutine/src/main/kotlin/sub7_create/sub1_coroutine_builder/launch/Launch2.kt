package sub7_create.sub1_coroutine_builder.launch

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * launch (CoroutineStart.LAZY)
 *
 *      launch 는 새로운 코루틴을 생성하는 coroutine builder 이다.
 *
 *      CoroutineStart.LAZY 옵션을 사용하면:
 *          - 코루틴은 생성되지만 즉시 실행되지 않는다.
 *          - 실행 가능한 상태(active)가 아니라 NEW 상태로 존재한다.
 *
 *      상태 흐름
 *          LAZY:
 *              NEW → (start / join) → ACTIVE → COMPLETED
 *
 *          DEFAULT:
 *              ACTIVE → COMPLETED
 *
 *      내부 동작
 *          - Job 객체 생성됨
 *          - CoroutineScope 에 등록됨
 *          - 하지만 실행 큐에 등록되지 않음 (스케줄링 대상 아님)
 *
 *      실행 시작 시점
 *          - job.start()
 *          - job.join()
 *          등의 호출 시 ACTIVE 상태로 전환되며 실행 시작
 *
 *      핵심
 *          - LAZY 는 "지연 실행"이 아니라 "지연 시작"
 *          - 코루틴을 미리 만들어두고 실행 시점을 제어할 수 있음
 */

fun main(): Unit = runBlocking {

    myPrint("runBlocking start")

    val job = launch(start = CoroutineStart.LAZY) {
        myPrint("hello launch")
    }

    myPrint("coroutine(launch) created (but not started)")

    delay(1000L)

    myPrint("launch coroutine, before start()")

    job.start() // Job 객체로 코루틴을 제어할 수 있다.

    myPrint("launch coroutine, after start()")
}

/**
 * 실행 결과
 *
 *      runBlocking start
 *      coroutine created (but not started)
 *      (1초 대기)
 *      before start()
 *      after start()
 *      hello launch
 */
