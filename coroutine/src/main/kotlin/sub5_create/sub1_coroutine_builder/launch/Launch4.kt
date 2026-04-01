package sub5_create.sub1_coroutine_builder.launch

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * launch + join
 *
 *      launch는 새로운 코루틴을 생성하고 Job을 반환한다.
 *      Job은 해당 코루틴 작업 자체를 나타내며,
 *      join()을 통해 해당 코루틴이 끝날 때까지 기다릴 수 있다.
 *
 *      join()
 *          - 대상 Job이 완료될 때까지 현재 코루틴을 suspend 한다.
 *          - 스레드를 blocking 하지 않는다.
 *          - "이 작업 끝날 때까지 다음으로 넘어가지 않겠다"는 의미
 *
 *      이 예제의 목적
 *          example1:
 *              - 두 개의 launch를 바로 실행
 *              - 두 코루틴이 동시에 진행되는 것처럼 동작
 *              - 총 수행 시간은 약 1초
 *
 *          example2:
 *              - 첫 번째 코루틴(job1)이 끝날 때까지 join()으로 기다림
 *              - 그 다음 두 번째 코루틴(job2) 실행
 *              - 순차 실행이 되므로 총 수행 시간은 약 2초
 */

fun main() {

    var start = System.currentTimeMillis()
    example1()
    var end = System.currentTimeMillis()
    myPrint("example1 = ${end - start} ms")

    start = System.currentTimeMillis()
    example2()
    end = System.currentTimeMillis()
    myPrint("example2 = ${end - start} ms")
}

private fun example1() = runBlocking {
    launch {
        delay(1000L)
        myPrint("job1 is done")
    }

    launch {
        delay(1000L)
        myPrint("job2 is done")
    }
}

private fun example2() = runBlocking {
    val job1 = launch {
        delay(1000L)
        myPrint("job1 is done")
    }

    job1.join()

    launch {
        delay(1000L)
        myPrint("job2 is done")
    }
}

/**
 * 실행 결과 예시
 *
 *      job1 is done
 *      job2 is done
 *      example1 = 약 1000 ms
 *
 *      job1 is done
 *      job2 is done
 *      example2 = 약 2000 ms
 *
 * 설명
 *      example1
 *          - job1, job2를 거의 동시에 launch 한다.
 *          - 두 launch 호출 시점에는 부모 코루틴이 아직 실행 중이므로
 *            자식 코루틴이 즉시 본문을 실행한다고 보면 안 된다.
 *          - 실제로는 두 자식 코루틴이 실행 가능 상태로 등록된 뒤,
 *            부모 블록의 남은 코드가 끝나거나 suspend 지점이 오면 event loop 가 실행을 넘긴다.
 *            부모 코루틴에는 suspend 지점이 없으므로 부모 코루틴이 모두 실행된 후, 자식 코루틴 중 하나가 실행 시작될 것이다. (단일 스레드 환경)
 *          - 두 코루틴 모두 delay(1000L) 후 완료되므로
 *            전체 시간은 약 1초 정도 걸린다.
 *          - 즉, 두 코루틴이 interleaving 되며 동시성 있게 실행된다.
 *            자식 코루틴에는 delay, yield, join 과 같은 suspend 함수가 존재한다.(delay)
 *                  따라서, 아래와 같이 동작함.
 *                  1. event loop 에 예약된 job1 이 실행된다. (단일 스레드이므로 job1 만 실행됨)
 *                  2. job1 은 바로 delay 에 의해 suspend 된다. (실행권을 넘김)
 *                  3. event loop 에 예약된 job2 가 실행된다. (단일 스레드이므로 job2 만 실행됨)
 *                  4. job2 는 바로 delay 에 의해 suspend 된다. (실행권을 넘김)
 *                  5. delay 가 끝난 job1 이 실행된다. (단일 스레드이므로 job1 만 실행됨)
 *                  6. job1 모든 실행코드 끝 (실행권을 넘김)
 *                  7. delay 가 끝난 job2 가 실행된다. (단일 스레드이므로 job2 만 실행됨)
 *                  8. job2 모든 실행코드 끝 (실행권을 넘김)
 *                  포인트는 1~2, 3~4, 5~6, 7~8 사이에는 해당 코루틴만 실행되고 다른 코루틴이 끼어들수 없다.
 *                      동시성이라해서 OS 스케줄러에 의한 스레드에서 처럼 뭔가 알수없는 더 작은 단위로 나누어져서 같이 실행하는 것처럼 상상할 수 있지만,
 *                      그것이 아님을 보여주기 위함. 즉, suspend 함수가 있음으로인해 실행권이 넘어가는것 뿐임.
 *
 *      example2
 *          - job1 실행 후 job1.join() 호출
 *          - 현재 코루틴은 job1이 끝날 때까지 suspend 된다.
 *          - job1 완료 후에야 job2를 launch 하므로
 *            결과적으로 순차 실행이 된다.
 *          - 따라서 전체 시간은 약 2초 정도 걸린다.
 *
 * 핵심
 *      - launch 여러 개를 바로 호출하면 코루틴들은 동시성 있게 실행될 수 있다.
 *      - join()을 사용하면 특정 코루틴의 완료를 기다린 뒤 다음 작업으로 넘어갈 수 있다.
 *      - join()은 스레드를 막는 것이 아니라 현재 코루틴만 기다리게 한다.
 *      - 기본 runBlocking 환경에서는 스레드가 하나이므로 실제 실행은 항상 하나씩만 된다.
 *      - 그럼에도 delay, join 같은 suspend 지점 덕분에 코루틴들은 번갈아 실행되며
 *        "동시에 진행되는 것처럼" 보일 수 있다.
 *
 * 주의
 *      - runBlocking은 자식 코루틴이 모두 끝날 때까지 반환되지 않는다.
 *      - 따라서 example1에서 join()을 명시적으로 호출하지 않아도
 *        runBlocking이 내부적으로 두 launch의 완료를 기다린다.
 */
