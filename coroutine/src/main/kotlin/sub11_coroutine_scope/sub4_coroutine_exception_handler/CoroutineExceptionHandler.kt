package sub11_coroutine_scope.sub4_coroutine_exception_handler

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * CoroutineExceptionHandler 란?
 *      코루틴에서 최종적으로 처리되지 않은 예외가 남았을 때 호출되는 handler 이다.
 *          - try-catch 처럼 예외를 바로 잡는 코드라기보다
 *          - 마지막 단계에서 예외를 기록(logging)하거나 대응하는 hook 에 가깝다.
 *
 *      주의
 *          - 특히 부모에게 전파될 자식 코루틴 예외는 자식 쪽 CoroutineExceptionHandler 가 처리하지 않는다.
 *          - launch 에 만 동작한다.
 *
 * 이 예제의 포인트
 *      1. root launch 에 붙인 CoroutineExceptionHandler 는 동작한다.
 *      2. runBlocking 의 자식 launch 에 붙인 CoroutineExceptionHandler 는 동작하지 않는다.
 *      3. 이유는 자식 예외가 먼저 부모(runBlocking)로 전파되기 때문이다.
 */
fun main(): Unit = runBlocking {

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        myPrint("handler: ${throwable::class.simpleName}, message=${throwable.message}")
    }

    /**
     * case 1) root launch + CoroutineExceptionHandler
     *
     *      CoroutineScope(Dispatchers.Default).launch(...) 는
     *      runBlocking 의 자식이 아닌 독립적인 root launch 를 만든다.
     *
     *      따라서 이 코루틴 안에서 처리되지 않은 예외가 발생하면
     *      부모 runBlocking 으로 전파되지 않고,
     *      이 root launch 에 붙어 있는 CoroutineExceptionHandler 가 최종 처리 지점이 된다.
     *
     *      join() 은 단지 job1 이 끝날 때까지 기다릴 뿐이다.
     *      launch 이므로 await 처럼 예외를 다시 던져받는 구조는 아니다.
     */
    val job1 = CoroutineScope(Dispatchers.Default).launch(coroutineExceptionHandler) {
        throw IllegalStateException("launch1 exception")
    }
    job1.join()
    myPrint("job1 end")

    /**
     * case 2) child launch + CoroutineExceptionHandler
     *
     *      이번에는 runBlocking 안에서 launch(...) 를 호출했다.
     *      즉, job2 는 runBlocking 의 자식 코루틴이다.
     *
     *      여기서 핵심은:
     *          자식 launch 의 예외는 먼저 부모(runBlocking)로 전파된다는 점이다.
     *
     *      그래서 자식에 붙인 CoroutineExceptionHandler 가
     *      root launch 때와 같은 방식으로 "최종 처리자"가 되지 못한다.
     *
     *      결과적으로 runBlocking 이 실패 흐름으로 들어갈 수 있고,
     *      아래의 myPrint("job2 end") 는 실행되지 않을 수 있다.
     *
     *      즉, CoroutineExceptionHandler 는
     *      "부모-자식 예외 전파 규칙을 무시하고 아무 예외나 중간에서 막는 장치"가 아니다.
     */
    val job2 = launch(coroutineExceptionHandler) {
        throw IllegalStateException("launch2 exception")
    }
    job2.join()
    myPrint("job2 end")
}

