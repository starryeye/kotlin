package sub8_create.sub1_coroutine_builder.launch

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * launch + CancellationException
 *
 *      Launch3 에서는 cancel() 이 "취소 요청"이고
 *      실제 종료는 suspend 지점에서 일어난다는 점을 봤다.
 *
 *      이번 예제는 그 suspend 지점에서 실제로 무엇이 일어나는지를 조금 더 명시적으로 본다.
 *      핵심은 delay() 가 취소 가능(cancellable)한 suspend 함수라는 점이다.
 *
 *      즉,
 *          - job.cancel() 을 호출하면 Job 상태가 cancelled 로 바뀌고
 *          - 이후 코루틴이 delay() 같은 cancellable suspend 함수에 도달해 있거나
 *            그 지점에서 재개되려 할 때
 *          - 내부적으로 CancellationException 이 발생하면서 코루틴이 종료된다.
 *
 *      중요한 점
 *          - cancel() 이 호출되는 순간 사용자가 직접 예외를 throw 하는 것이 아니다.
 *          - 취소 가능 suspend 함수가 취소 상태를 확인한 뒤
 *            CancellationException 으로 실행을 중단시키는 것이다.
 *
 * 참고
 *      CancellationException 에 대해서는 sub11 > sub5_cancellation_exception 에서 한번 더 다룬다.
 */
fun main(): Unit = runBlocking {

    val job = launch {
        try {
            myPrint("child start")

            repeat(5) { index ->
                myPrint("before delay ${index + 1}")
                delay(500L)
                myPrint("after delay ${index + 1}")
            }
        } catch (e: CancellationException) {
            myPrint("caught CancellationException: ${e::class.simpleName}")
            throw e
        } finally {
            myPrint("finally block")
        }
    }

    delay(1300L)
    myPrint("parent: cancel request")
    job.cancel()
    job.join()
    myPrint("parent: job is completed")
}

/**
 * 실행 결과 예시
 *
 *      child start
 *      before delay 1
 *      after delay 1
 *      before delay 2
 *      after delay 2
 *      before delay 3
 *      parent: cancel request
 *      caught CancellationException: JobCancellationException
 *      finally block
 *      parent: job is completed
 *
 * 설명
 *      - 세 번째 delay(500L) 대기 중에 부모가 cancel() 을 호출한다.
 *      - delay 는 cancellable suspend 함수이므로 취소 상태를 감지한다.
 *      - 그 결과 CancellationException 의 한 종류인 JobCancellationException 이 발생한다.
 *      - catch 블록에서 그 예외를 관찰할 수 있고, finally 도 실행된다.
 *      - 예외를 다시 던지지 않으면 취소를 정상 종료처럼 삼켜버릴 수 있으므로
 *        보통 CancellationException 은 필요한 정리만 하고 다시 던지는 편이 안전하다.
 *
 * 핵심
 *      - 코루틴 취소는 보통 CancellationException 을 통해 전파된다.
 *      - delay 에서도 실제로는 이 예외가 발생하며, 그래서 다음 코드로 진행하지 못하고 종료된다.
 */
