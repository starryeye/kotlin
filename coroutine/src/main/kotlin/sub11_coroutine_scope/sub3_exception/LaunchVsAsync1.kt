package sub11_coroutine_scope.sub3_exception

import kotlinx.coroutines.*
import util.myPrint

/**
 * launch vs async 1 - root coroutine exception
 *
 *      이 예제는 CoroutineScope(Dispatchers.Default) 로 "독립적인 root 코루틴"을 각각 하나씩 만든 경우를 본다.
 *
 *      여기서는 두 코루틴이 runBlocking 의 자식이 아니다.
 *      root async 와 root launch 를 따로 시작한 상황이다.
 *
 *      이 경우 launch 와 async 의 차이를 알아본다.
 *
 *      launch
 *          - 결과값이 없는 작업용 builder
 *          - root launch 에서 예외가 발생하면 그 예외를 받아줄 부모가 없으므로
 *                  현재 root launch 내부에서 처리되지 않은 채 남는다.
 *          - 그래서 그 예외는 main 의 runBlocking 으로 되돌아오는 것이 아니라 해당 코루틴의 기본 uncaught exception 처리 경로로 전달된다.
 *          - 주의
 *              JVM 에서는 이 과정에서 보통 stack trace 가 stderr 에 출력된다.
 *              하지만 이것은 "main 스레드가 그 예외를 catch/receive 했다"는 뜻이 아니다.
 *              예외는 worker thread / coroutine 예외 처리 경로에서 처리되고,
 *              main runBlocking 쪽으로 정상 복귀하는 구조가 아니다.
 *
 *      async
 *          - 결과값이 있는 작업용 builder
 *          - root async 에서 예외가 발생해도 그 예외를 즉시 바깥으로 던지지 않고 Deferred 안에 저장한다.
 *          - 따라서 await() 를 호출해야 호출한 쪽에서 그 예외가 다시 던져진다.
 *          - await() 하지 않으면 main 의 runBlocking 까지 즉시 전파되지 않는다.
 *
 *      이 예제의 포인트
 *          1. root async 예외는 await() 하기 전까지 main/runBlocking 으로 바로 전파되지 않는다.
 *          2. root launch 예외는 부모가 없어서 runBlocking 으로 회수되지 않고 uncaught 예외 경로로 간다.
 *          3. join() 은 완료만 기다릴 뿐, async 내부 예외를 다시 던져주지 않는다.
 */
fun main(): Unit = runBlocking {

    /**
     * root async 예외
     *
     *      아래 Deferred 는 독립 scope 에서 실행되다가
     *      IllegalStateException 으로 실패한다.
     *
     *      하지만 async 는 예외를 Deferred 안에 저장하므로
     *      여기서 job1.join() 만 호출하면 "끝날 때까지 기다리기만" 하고,
     *      예외를 현재 runBlocking(main) 쪽으로 다시 던지지는 않는다.
     *
     *      만약 실제로 예외를 받고 싶다면 job1.await() 를 호출해야 하고,
     *      그 시점에 IllegalStateException 이 현재 코루틴으로 전파된다.
     */
    val job1 = CoroutineScope(Dispatchers.Default).async {
        throw IllegalStateException("async exception")
    }

    job1.join()

    /**
     * root launch 예외
     *
     *      launch 는 결과를 담아둘 그릇(Deferred)이 없고, 예외를 await() 같은 방식으로 꺼내는 구조도 아니다.
     *
     *      그래서 독립 scope 의 root launch 에서 예외가 발생하면
     *      그 예외는 현재 runBlocking(main) 으로 되돌아오지 않는다.
     *      대신 해당 root coroutine 의 uncaught 예외 처리 경로로 전달된다.
     *
     *      JVM 기준으로 보면 보통 다음처럼 이해하면 된다.
     *          1. worker thread 에서 실행 중이던 root launch 가 실패한다.
     *          2. 그 예외는 main thread 로 throw 되는 것이 아니다.
     *          3. 코루틴 런타임 / thread 의 uncaught 예외 처리 쪽으로 넘어간다.
     *          4. 그 결과 stack trace 가 stderr 에 출력될 수 있다.
     *
     *      예외로 인해 프로세스 종료가 된것은 아니다!
     *          - stack trace 가 출력되었다고 해서 곧바로 "main thread 가 예외로 죽었다"는 뜻은 아니다.
     *          - 이 예제에서는 main 은 여전히 delay(1000) 를 수행후 "runBlocking end"를 출력하고 runBlocking 을 끝낸다.
     *
     *      아래 delay(1000) 는 launch 코루틴이 실행될 시간을 주기 위한 것이다.
     *      delay 가 없다면 main 쪽 runBlocking 이 너무 빨리 끝나서
     *      그 예외가 처리되는 시점을 관찰하기 어려울 수 있다.
     */
    val job2 = CoroutineScope(Dispatchers.Default).launch {
        throw IllegalStateException("launch exception")
    }

    delay(1000)
    myPrint("runBlocking end") // 정상 출력됨
}
