package sub8_create.sub1_coroutine_builder.launch

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * launch + isActive
 *
 *      Launch3 에서는 cancel() 이 "취소 요청"이고,
 *      실제 종료는 suspend 지점에서 협력적으로 일어난다는 점을 봤다.
 *
 *      그러면 이런 질문이 생긴다.
 *          "suspend 함수가 전혀 없는 반복 작업은 어떻게 취소하지?"
 *
 *      그때 사용하는 대표적인 방법이 isActive 확인이다.
 *
 *      isActive
 *          - 현재 코루틴이 아직 살아 있는지 나타내는 값
 *          - 취소되면 false 가 된다.
 *          - 긴 계산 루프, polling 루프처럼 suspend 지점이 드문 코드에서
 *            협력적으로 취소에 반응할 때 자주 사용한다.
 *
 *      핵심
 *          - cancel() 만 호출해서는 CPU 바운드 루프가 바로 멈추지 않을 수 있다.
 *          - 루프 안에서 isActive 를 확인해야 취소 요청을 스스로 받아들이고 빠져나올 수 있다.
 */
fun main(): Unit = runBlocking {

    val job = launch {
        var round = 0L

        while (isActive) {
            round++

            // suspend 없는 계산 루프라서 직접 취소 상태를 확인해야 한다.
            if (round % 5_000_000L == 0L) {
                myPrint("working... round=$round")
            }
        }

        myPrint("loop finished because isActive=false")
    }

    delay(1000L)
    myPrint("cancel request")
    job.cancelAndJoin()
    myPrint("job is cancelled")
}

/**
 * 실행 결과 예시
 *
 *      working... round=5000000
 *      working... round=10000000
 *      working... round=15000000
 *      cancel request
 *      loop finished because isActive=false
 *      job is cancelled
 *
 * 설명
 *      - 자식 코루틴은 delay 없이 계속 계산만 수행한다.
 *      - 이런 코드는 suspend 지점이 없으므로 cancel() 만으로는 즉시 멈추지 않는다.
 *      - 하지만 while (isActive) 로 취소 상태를 계속 확인하면
 *        취소 요청이 들어왔을 때 루프를 빠져나올 수 있다.
 *      - cancelAndJoin() 은
 *          1. 취소 요청을 보내고
 *          2. 해당 Job 이 완전히 끝날 때까지 기다린다.
 *
 * 핵심
 *      - delay, yield 같은 cancellable suspend 함수가 있으면 그 지점에서 취소를 감지한다.
 *      - suspend 지점이 없는 CPU 작업은 isActive 같은 명시적 확인이 필요하다.
 *
 * 주의
 *      - 실제 CPU 바운드 계산에서는 루프마다 매번 무거운 로그를 찍으면 오히려 성능이 왜곡된다.
 *      - 여기서는 취소 확인 흐름을 보이기 위해 일정 간격으로만 출력했다.
 */
