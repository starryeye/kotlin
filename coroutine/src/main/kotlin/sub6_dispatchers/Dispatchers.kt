package sub6_dispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import util.myPrint

/**
 * Dispatcher
 *
 *      Dispatcher 는 코루틴이 어느 스레드 / 스레드 풀에서 실행될지 결정하는 정책이다.
 *
 *      지금까지 예제에서는 대부분 runBlocking 기본 문맥만 사용했다.
 *      그래서 "현재 스레드 하나에서 코루틴이 번갈아 실행되는 모습"이 주로 보였다.
 *
 *      그런데 suspend / resume 이 가능하다는 사실만으로는
 *      "왜 어떤 코루틴은 다른 스레드에서 실행될 수 있는가?"가 충분히 설명되지 않는다.
 *      그 지점을 설명하는 개념이 Dispatcher 이다.
 *
 *      핵심 역할
 *          - 코루틴 시작 위치를 정한다.
 *          - suspend 후 resume 될 때 어느 실행 기반으로 보낼지 정한다.
 *          - 같은 Dispatcher 를 쓰면 같은 실행 풀 안에서 움직일 가능성이 높다.
 *          - 다른 Dispatcher 로 바꾸면 다른 스레드 / 스레드 풀로 이동할 수 있다.
 *
 *      자주 보는 Dispatcher
 *          - runBlocking 기본값 : 현재 스레드 기반
 *          - Dispatchers.Default : CPU 바운드 작업용 공용 background thread pool (ForkJoinPool 아니라고한다..)
 *          - Dispatchers.IO      : blocking I/O 작업용 공용 thread pool
 *
 *      아래 예제에서 보고 싶은 것
 *          1. 별도 Dispatcher 를 지정하지 않으면 부모 문맥을 상속한다.
 *          2. Dispatchers.Default 를 지정하면 worker thread 에서 실행될 수 있다.
 *          3. withContext(Dispatchers.IO) 는 현재 코루틴의 실행 문맥을 잠시 바꾼다.
 *          4. withContext 블록이 끝나면 원래 문맥으로 돌아온다.
 */
fun main(): Unit = runBlocking {

    myPrint("runBlocking start")

    launch {
        myPrint("launch without dispatcher: inherit parent context")
        delay(100L)
        myPrint("still on inherited context after delay")
    }

    launch(Dispatchers.Default) {
        myPrint("launch with Dispatchers.Default")
        delay(100L)
        myPrint("resumed on Default dispatcher")
    }

    withContext(Dispatchers.IO) {
        myPrint("withContext(Dispatchers.IO) start")
        delay(100L)
        myPrint("withContext(Dispatchers.IO) end")
    }

    myPrint("back to runBlocking context")
}

/**
 * 실행 결과 예시
 *
 *      [main] runBlocking start
 *      [DefaultDispatcher-worker-1] launch with Dispatchers.Default
 *      [main] launch without dispatcher: inherit parent context
 *      [DefaultDispatcher-worker-2] withContext(Dispatchers.IO) start
 *      [main] still on inherited context after delay
 *      [DefaultDispatcher-worker-1] resumed on Default dispatcher
 *      [DefaultDispatcher-worker-2] withContext(Dispatchers.IO) end
 *      [main] back to runBlocking context
 *
 * 설명
 *      - 첫 번째 launch 는 Dispatcher 를 지정하지 않았으므로 부모(runBlocking)의 문맥을 상속한다.
 *        그래서 main 스레드에서 실행되는 모습을 볼 가능성이 높다.
 *
 *      - 두 번째 launch 는 Dispatchers.Default 를 명시했으므로
 *        CPU 바운드 작업용 공용 background thread 에서 실행된다.
 *
 *      - withContext(Dispatchers.IO) 는 "새 코루틴을 만드는 것"이 아니라
 *        현재 코루틴의 실행 문맥을 잠시 IO 쪽으로 바꾼다.
 *        블록이 끝나면 다시 원래 문맥(runBlocking 쪽)으로 돌아온다.
 *
 *      - thread 이름은 실행 환경에 따라 달라질 수 있다.
 *        중요한 것은 "Dispatcher 가 실행 위치를 결정한다"는 점이다.
 *
 * 핵심
 *      - suspend / resume 은 코루틴의 실행 상태를 저장하고 이어가는 메커니즘이다.
 *      - Dispatcher 는 그 코루틴을 어느 스레드 기반에서 돌릴지 결정하는 정책이다.
 *      - 따라서 다음 장의 context switching 은
 *        "Dispatcher 변경 시 왜 스레드 전환이 생길 수 있는가"를 이해하는 단계가 된다.
 */
