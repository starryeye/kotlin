package sub11_coroutine_scope.sub3_exception

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * launch vs async - exception
 *
 *      launch 와 async 는 둘 다 새로운 코루틴을 시작하지만,
 *      예외를 바깥에 드러내는 방식이 다르다.
 *
 *      launch
 *          - 결과값이 없는 작업용 builder
 *          - 예외가 발생하면 보통 즉시 "처리되지 않은 예외"로 보고된다.
 *          - 따라서 root launch 에서 예외가 나면 콘솔에 stack trace 가 바로 보이기 쉽다.
 *
 *      async
 *          - 결과값이 있는 작업용 builder
 *          - 예외가 발생해도 바로 바깥으로 던지지 않고 Deferred 안에 저장한다.
 *          - 따라서 await() 를 호출해야 그 예외를 호출한 쪽에서 직접 받을 수 있다.
 *
 *      왜 이런 차이가 있나?
 *          - async 는 "나중에 결과를 꺼내 쓰는 작업"이기 때문이다.
 *          - 성공 결과뿐 아니라 실패(예외)도 Deferred 의 결과 일부처럼 보관된다.
 *
 *      이 예제의 포인트
 *          1. async 예외는 await() 하기 전까지 호출부에서 바로 보이지 않는다.
 *          2. launch 예외는 처리되지 않으면 즉시 보고된다.
 *          3. join() 은 완료만 기다릴 뿐, async 내부 예외를 다시 던져주지 않는다.
 */
fun main(): Unit = runBlocking {

    /**
     * async 예외
     *
     *      아래 Deferred 는 생성 직후 백그라운드에서 실행되다가
     *      IllegalStateException 으로 실패한다.
     *
     *      하지만 async 는 예외를 Deferred 안에 저장하므로
     *      여기서 job1.join() 만 호출하면 "끝날 때까지 기다리기만" 하고,
     *      예외를 현재 코루틴 쪽으로 다시 던지지는 않는다.
     *
     *      만약 실제로 예외를 받고 싶다면
     *          job1.await()
     *      를 호출해야 한다.
     */
    val job1 = CoroutineScope(Dispatchers.Default).async {
        throw IllegalStateException("async exception")
    }

    job1.join()

    /**
     * launch 예외
     *
     *      launch 는 결과를 담아둘 그릇(Deferred)이 없고,
     *      예외를 await() 같은 방식으로 꺼내는 구조도 아니다.
     *
     *      그래서 root launch 에서 예외가 발생하면
     *      기본 예외 처리 경로로 바로 보고된다.
     *
     *      아래 delay(1000) 는 launch 코루틴이 실행될 시간을 주기 위한 것이다.
     *      delay 가 없다면 main 쪽 runBlocking 이 너무 빨리 끝나서
     *      예외 출력 타이밍을 관찰하기 어려울 수 있다.
     */
    val job2 = CoroutineScope(Dispatchers.Default).launch {
        throw IllegalStateException("launch exception")
    }

    delay(1000)
}
