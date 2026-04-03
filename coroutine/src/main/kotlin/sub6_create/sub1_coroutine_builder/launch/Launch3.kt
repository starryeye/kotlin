package sub6_create.sub1_coroutine_builder.launch

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * launch + cancellation
 *
 *      launch 는 Job 을 반환하며, 이 Job 을 통해 코루틴을 제어할 수 있다.
 *
 *      cancel()
 *          - 해당 코루틴에 취소 신호를 전달한다.
 *          - 즉시 강제 종료되는 것이 아니라, 다음 suspension 지점에서 취소된다.
 *
 *      실행 흐름
 *          - launch 로 코루틴 생성 및 실행
 *          - 1 ~ 5까지 출력 (500ms 간격)
 *          - runBlocking 에서 1300ms 후 cancel() 호출
 *
 *      취소 동작
 *          - 코루틴은 "협력적(cooperative)"으로 취소된다.
 *          - 즉, suspend 함수(delay 등)에서 취소 여부를 확인하고 종료된다.
 *
 *          - delay()는 cancellable suspend 함수이므로
 *            cancel() 호출 시 즉시 CancellationException이 발생하며 코루틴 종료
 *
 *      핵심
 *          - cancel()은 "즉시 종료"가 아니라 "취소 요청"이다.
 *          - 실제 종료는 suspend 지점에서 발생한다.
 *
 *      주의
 *          - suspend 지점이 없는 코루틴은 cancel()이 바로 동작하지 않을 수 있다.
 *          - (무한 루프 + suspend 없음 → 취소 안됨)
 */

fun main(): Unit = runBlocking {

    val job = launch {
        (1..5).forEach {
            myPrint(it.toString())
            delay(500L)
        }
    }

    delay(1300L)
    job.cancel()
}

/**
 * 실행 결과 (예상)
 *
 *      1
 *      (0.5초)
 *      2
 *      (0.5초)
 *      3
 *
 *      → 이후 cancel() 호출로 종료
 *
 * 설명
 *      - 1 출력 (0ms)
 *      - 2 출력 (500ms)
 *      - 3 출력 (1000ms)
 *      - 1300ms 시점에 cancel() 호출
 *
 *      - 다음 delay(500L) 지점에서 취소 감지 → 코루틴 종료
 *
 *      따라서 4, 5는 출력되지 않는다.
 *
 * 핵심 한 줄
 *      코루틴 취소는 "강제 종료"가 아니라 "협력적 중단"이다.
 */
