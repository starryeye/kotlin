package sub5_create.sub1_coroutine_builder.run_blocking

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
 * 여기서는 coroutine builder 중 runBlocking 에 대해 알아본다.
 *
 * runBlocking
 *      일반 함수(main 등)에서 코루틴을 시작하기 위한 builder
 *
 *      특징
 *          - 새로운 코루틴을 생성하고 실행한다.
 *          - 현재 스레드를 블로킹(blocking)하면서 코루틴이 끝날 때까지 기다린다.
 *          - CoroutineScope 를 생성하며, 해당 scope 안에서 코루틴을 실행할 수 있다.
 *
 *      실행 모델
 *          - runBlocking 은 기본적으로 단일 스레드에서 동작한다.
 *          - 내부 event loop 를 통해 코루틴을 스케줄링한다.
 *          - 별도의 Dispatcher 를 지정하지 않으면 현재 스레드(main thread)에서 실행된다.
 *
 *      동작 흐름
 *          1. runBlocking 호출
 *          2. 현재 스레드를 블로킹하고 루트 코루틴 생성
 *          3. block 내부 코드 실행
 *          4. 자식 코루틴이 있다면 모두 완료될 때까지 대기
 *          5. 모든 작업이 끝나면 runBlocking 종료
 *
 */
fun main() {

    myPrint("main start")

    runBlocking {
        myPrint("runBlocking start")
        launch {
            myPrint("launch start")

            // 현재 코루틴을 suspend시키고, 지정한 시간이 지난 뒤 다시 resume되도록 예약한다.
            // 그래서 그 시간 동안 해당 스레드는 다른 코루틴이나 다른 작업을 처리할 수 있다.
            delay(1000)

            myPrint("launch end")
        }
        myPrint("runBlocking end")
    }

    myPrint("main end")
}

/**
 * 실행 결과
 *      main start
 *      runBlocking start
 *      runBlocking end
 *      launch start
 *      (1초 후)
 *      launch end
 *      main end
 *
 * 설명
 *      - runBlocking 내부에서 launch로 자식 코루틴 생성
 *      - launch는 비동기로 실행되지만,
 *        runBlocking은 자식 코루틴이 끝날 때까지 종료되지 않음
 *
 *      즉, runBlocking은 "코루틴이 끝날 때까지 기다리는 blocking builder"
 *
 * 주의
 *      - runBlocking은 스레드를 블로킹하기 때문에
 *        서버 코드에서는 사용을 최소화해야 한다.
 *      - 주로 테스트 코드나 main 함수에서 사용된다.
 *
 * 핵심
 *      - 일반 코드 ↔ 코루틴 세계를 연결하는 bridge 역할
 */