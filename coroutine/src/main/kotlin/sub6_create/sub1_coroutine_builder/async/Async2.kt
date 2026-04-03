package sub6_create.sub1_coroutine_builder.async

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * async 를 여러 개 사용하면
 * "결과가 필요한 여러 작업"을 먼저 시작해두고,
 * 나중에 await() 로 결과를 모을 수 있다.
 *
 * 이 예제에서는:
 *      - function1()
 *      - function2()
 * 두 함수를 각각 async 로 실행한다.
 *
 * 흐름
 *      - async 가 호출되는 순간 각각 자식 코루틴이 생성된다.
 *      - 각 코루틴은 Deferred 로 결과를 보관한다.
 *      - 여기서는 두 작업을 먼저 시작한 뒤,
 *        마지막에 await() 로 결과를 가져온다.
 *
 * 중요한 점
 *      - 이 예제는 "여러 스레드에서 실제 병렬 계산"을 설명하는 예제가 아니다.
 *      - 핵심은 suspend 되는 작업들의 대기 시간을 겹치는 데 있다.
 *      - 그래서 각 작업이 1초씩 걸려도 총 시간은 약 2초가 아니라 약 1초가 된다.
 *
 * coroutine 이 바꾸는 것 / 바꾸지 않는 것
 *      - coroutine 은 blocking 작업을 자동으로 non-blocking 으로 바꾸지 않는다.
 *      - 즉, Kotlin 을 쓴다고 해서 I/O 가 마법처럼 안 막히는 것은 아니다.
 *      - coroutine 이 해주는 일은 비동기 흐름을 suspend / resume 으로 다루고,
 *        코드를 순차적으로 읽히게 만드는 것이다.
 *
 * case 1) delay 같은 suspend 작업
 *      - delay 는 스레드를 block 하지 않는다.
 *      - 현재 코루틴만 suspend 되고, 스레드는 다른 작업에 사용할 수 있다.
 *      - 이 예제는 이 경우를 단순화해서 보여준다.
 *
 * case 2) blocking API 호출
 *      - 예: Thread.sleep(), 전통적인 JDBC 호출, blocking HTTP client, blocking 파일 읽기
 *      - 이런 작업을 suspend 함수 안에서 호출해도 실제 수행 스레드는 block 된다.
 *      - 즉, coroutine 을 써도 "기다리는 스레드"가 완전히 사라지는 것은 아니다.
 *      - 특히 한 스레드 문맥에서 이런 blocking 작업을 실행하면
 *        대기 시간이 제대로 겹치지 못해 전체 시간이 사실상 순차 실행처럼 늘어날 수 있다.
 *      - 다만 async / await 구조를 통해 여러 작업을 함께 시작하고 결과를 모으는 코드는
 *        더 명확하게 작성할 수 있다.
 *      - 실무에서는 이런 blocking 작업을 보통 Dispatchers.IO 에서 처리한다.
 *
 * case 3) non-blocking API 호출
 *      - 예: non-blocking HTTP client, non-blocking DB driver, NIO 기반 I/O
 *      - 이런 작업은 응답을 기다리는 동안 스레드를 붙잡지 않을 수 있다.
 *      - 이때는 코루틴만 suspend 되고, 스레드는 다른 작업을 처리하러 갈 수 있다.
 *      - 응답이 오면 코루틴이 다시 resume 된다.
 *      - 이 경우에는 실제로 "기다리기 위한 전용 스레드"를 크게 줄일 수 있다.
 *
 * 의의
 *      - async 의 핵심은 "독립적인 작업을 먼저 시작해두고 결과를 나중에 모은다"는 데 있다.
 *      - 성능 이득은 작업의 성격에 따라 다르다.
 *      - blocking API 라면 코드 구조 개선과 작업 분리가 더 중요한 의미가 있고,
 *        non-blocking API 라면 스레드 점유 감소와 높은 동시 처리 효율까지 기대할 수 있다.
 */
fun main(): Unit = runBlocking  {

    val startTime = System.currentTimeMillis()
    val deferred1 = async {
        function1()
    }
    val deferred2 = async {
        function2()
    }

    myPrint("total = ${deferred1.await()} + ${deferred2.await()}")
    val endTime = System.currentTimeMillis()
    println("elapsed = ${endTime - startTime} ms")
}

suspend fun function1(): Int {
    delay(1000)
    return 1
}

suspend fun function2(): Int {
    delay(1000)
    return 2
}

/**
 * 실행 결과
 *      total = 1 + 2
 *      elapsed = 약 1000 ms
 *
 * 설명
 *      - function1(), function2() 는 각각 1초가 걸리는 suspend 함수이다.
 *      - 두 함수를 async 로 먼저 시작했기 때문에 대기 구간이 서로 겹친다.
 *      - 이후 await() 를 호출할 때는 이미 두 작업이 거의 끝난 상태일 수 있다.
 *      - 따라서 전체 시간은 1초 + 1초가 아니라 약 1초 정도가 된다.
 *
 * 이 예제를 실제 상황에 대응시키면
 *      - 현재의 delay 는 "응답을 기다리는 시간"을 단순화한 것이다.
 *      - 따라서 외부 API 호출, DB 조회, 파일 읽기 같은 상황으로 치환해 생각할 수 있다.
 *
 * case 1) 실제 호출이 blocking 이라면
 *      - 각 작업을 수행하는 스레드는 호출이 끝날 때까지 block 된다.
 *      - 그래서 coroutine 이 있다고 해도 blocking 자체가 사라지지는 않는다.
 *      - 현재 예제처럼 한 스레드 문맥이라면 function1(), function2() 의 blocking 시간이
 *        서로 겹치지 못해서 전체 시간은 결국 약 2초가 된다.
 *      - 다만 여러 작업을 함께 시작하고 결과를 조합하는 흐름은 더 명확하게 표현할 수 있다.
 *
 * case 2) 실제 호출이 non-blocking 이라면
 *      - 응답을 기다리는 동안 스레드를 붙잡지 않을 수 있다.
 *      - 코루틴만 suspend 되고, 스레드는 다른 요청이나 다른 코루틴 처리에 재사용될 수 있다.
 *      - 이 경우 async 는 여러 외부 요청의 대기 시간을 겹쳐
 *        전체 응답 시간을 줄이는 데 특히 효과적이다.
 *
 */
