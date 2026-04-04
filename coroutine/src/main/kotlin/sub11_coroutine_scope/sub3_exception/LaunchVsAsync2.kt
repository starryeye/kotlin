package sub11_coroutine_scope.sub3_exception

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * launch vs async 2 - child coroutine exception
 *
 *      이 예제는 LaunchVsAsync1 과 다르게 runBlocking 안에서 바로 async / launch 를 호출한다.
 *
 *      즉, 여기서 만들어지는 코루틴들은 runBlocking 의 자식 코루틴들이다.("독립적인 root coroutine"이 아님)
 *
 *      이 상황에서는 launch 와 async 의 차이보다
 *      "자식 코루틴의 실패가 부모로 전파된다"는 점이 더 중요하다.
 *
 *      핵심
 *          - 자식 launch 가 실패하면 그 예외가 부모(runBlocking) 쪽으로 전파된다.
 *          - 자식 async 가 실패해도, 그 실패는 부모(runBlocking)를 취소시키는 원인이 된다.
 *          - 즉, 부모-자식 관계 안에서는
 *            async 라고 해서 예외가 완전히 숨겨지는 것이 아니다.
 *
 *      다시 말해
 *          - root async  : 예외를 Deferred 안에 저장하는 성격이 강하다.
 *          - child async : 부모-자식 관계 안에서는 부모 취소에 영향을 준다.
 *
 *      이 예제의 포인트
 *          1. async 예외도 부모 scope 안에서는 부모(runBlocking)까지 영향을 줄 수 있다.
 *          2. launch 예외도 마찬가지로 부모(runBlocking)로 전파된다.
 *          3. 따라서 structured concurrency 안에서는
 *             "누구의 자식인가"가 launch/async 차이만큼 중요하다.
 */
fun main(): Unit = runBlocking {

    val job1 = async {
        throw IllegalStateException("async exception")
    }

    /**
     * 여기서 join() 은 job1 이 끝날 때까지 기다리기만 한다.
     *
     * 하지만 job1 은 runBlocking 의 자식이므로 예외가 부모(runBlocking)에까지 전파된다.
     *
     * 즉, LaunchVsAsync1 의 root async 처럼
     * "Deferred 안에 저장되어 main 과 분리된 채 남는 상황"과는 다르다.
     */
    job1.join()

    val job2 = launch {
        throw IllegalStateException("launch exception")
    }

    /**
     * launch 역시 runBlocking 의 자식이므로 예외가 부모(runBlocking) 쪽으로 전파된다.
     *
     * 아래 delay 는 자식 코루틴들이 실행될 시간을 주기 위한 코드다.
     * 다만 실제로는 그 전에 부모가 취소/실패 흐름으로 들어갈 수도 있다.
     */
    delay(1000)
}
