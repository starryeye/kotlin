package sub4_blocking_vs_suspend

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * delay() vs Thread.sleep()
 *
 * 목적
 *      delay() 가 "코루틴을 suspend" 하고 Thread.sleep() 은 "스레드를 block" 하는데 둘의 차이를 알아본다.
 *
 * 핵심 질문
 *      - delay() 를 호출하면 누가 기다리는가?
 *      - Thread.sleep() 을 호출하면 누가 멈추는가?
 *
 * 정답
 *      - delay(): 현재 코루틴이 suspend 된다. 스레드는 block 되지 않는다.
 *      - Thread.sleep(): 현재 스레드가 block 된다. 그 스레드 위 코루틴들도 같이 막힌다.
 *
 * 중요한 점
 *      - coroutine 은 blocking 작업을 자동으로 non-blocking 으로 바꾸지 않는다.
 *      - 따라서 suspend 함수 안에서 Thread.sleep() 을 써도,
 *        실제로는 "코루틴스럽게 보이는 blocking 코드"일 뿐이다.
 *
 * 이 예제의 환경
 *      - 두 예제 모두 runBlocking 기본 문맥에서 실행된다.
 *      - 즉, 기본적으로 현재 스레드 하나를 기준으로 동작한다고 보면 된다.
 *      - 그래서 delay 와 Thread.sleep 의 차이가 더 분명하게 드러난다.
 */
fun main() {

    myPrint("example1 start")
    var start = System.currentTimeMillis()
    example1()
    var end = System.currentTimeMillis()
    myPrint("example1 elapsed = ${end - start} ms")

    println()

    myPrint("example2 start")
    start = System.currentTimeMillis()
    example2()
    end = System.currentTimeMillis()
    myPrint("example2 elapsed = ${end - start} ms")
}

private fun example1() = runBlocking {
    launch {
        myPrint("job1 start")
        delay(1000L)
        myPrint("job1 end")
    }

    launch {
        myPrint("job2 start")
        delay(1000L)
        myPrint("job2 end")
    }
}

private fun example2() = runBlocking {
    launch {
        myPrint("job1 start")
        Thread.sleep(1000L)
        myPrint("job1 end")
    }

    launch {
        myPrint("job2 start")
        Thread.sleep(1000L)
        myPrint("job2 end")
    }
}

/**
 * 실행 결과 예시
 *
 *      [main] example1 start
 *      [main] job1 start
 *      [main] job2 start
 *      [main] job1 end
 *      [main] job2 end
 *      [main] example1 elapsed = 약 1000 ms
 *
 *      [main] example2 start
 *      [main] job1 start
 *      (1초 대기)
 *      [main] job1 end
 *      [main] job2 start
 *      (1초 대기)
 *      [main] job2 end
 *      [main] example2 elapsed = 약 2000 ms
 *
 * 설명
 *      example1
 *          - job1 이 delay(1000L) 에 도달하면 현재 코루틴만 suspend 된다.
 *          - 스레드는 비지 않게 묶이지 않으므로, 같은 스레드에서 job2 도 실행될 수 있다.
 *          - 그래서 두 delay 의 "기다리는 시간"이 겹친다.
 *          - 결과적으로 전체 시간은 약 1초가 된다.
 *
 *      example2
 *          - job1 이 Thread.sleep(1000L) 에 도달하면 현재 스레드가 그대로 멈춘다.
 *          - 스레드가 멈췄으므로 같은 스레드에서 실행되어야 할 job2 는 시작조차 못 한다.
 *          - job1 의 sleep 이 끝난 뒤에야 job2 가 실행된다.
 *          - 결과적으로 전체 시간은 약 2초가 된다.
 *
 * 이 예제로부터 얻어야 할 결론
 *      - delay 는 non-blocking 대기이다. "코루틴만 기다린다."
 *      - Thread.sleep 은 blocking 대기이다. "스레드 자체가 기다린다."
 *      - 따라서 코루틴에서 시간 이득이 나는 이유는
 *        "여러 스레드가 항상 병렬 계산해서"가 아니라,
 *        suspend 가능한 대기 시간을 겹칠 수 있기 때문인 경우가 많다.
 *      - 참고로 delay 역시 아무것도 없이 저절로 끝나는 것은 아니고,
 *        내부적으로 타이머 / scheduler 같은 메커니즘이 시간을 관리한다.
 *      - 중요한 점은 그 시간을 "원래 작업 스레드가 직접 기다리지 않는다"는 것이다.
 *
 * 실제 I/O 에 대응시키면
 *      - non-blocking API 는 delay 쪽에 가깝다.
 *          응답 대기 동안 코루틴만 suspend 되고, 스레드는 다른 일에 쓸 수 있다.
 *      - blocking API 는 Thread.sleep 쪽에 가깝다.
 *          호출이 끝날 때까지 수행 스레드가 묶인다.
 *
 * 주의
 *      - 여러 스레드(예: Dispatchers.IO)를 쓰면 blocking 작업도 동시에 처리할 수는 있다.
 *      - 하지만 그것은 "blocking 이 non-blocking 으로 바뀐 것"이 아니라
 *        "막히는 작업을 여러 스레드에 나눠서 버틴 것"이다.
 */
