package sub7_create.sub1_coroutine_builder.async

import kotlinx.coroutines.async
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
 * 여기서는 coroutine builder 중 async 에 대해 알아본다.
 *
 * async
 *      결과값을 반환하는 비동기 코루틴을 생성하는 builder
 *
 *      특징
 *          - CoroutineScope 의 확장 함수이다.
 *          - 반드시 CoroutineScope 안에서만 호출 가능하다.
 *          - Deferred<T> 를 반환한다.
 *          - Deferred 는 "미래에 받을 결과값"을 나타내는 객체이다.
 *                  Deferred 는 Job 을 상속함.
 *          - 비동기적으로 실행된다. (호출 즉시 결과가 준비되는 것은 아니다)
 *
 *      launch 와의 차이
 *          - launch 는 결과값 없이 Job 을 반환한다.
 *          - async 는 결과값이 있는 작업을 만들고 Deferred 를 반환한다.
 *          - 결과가 필요할 때는 await() 로 받아온다.
 *
 *      await()
 *          - Deferred 의 결과가 준비될 때까지 현재 코루틴을 suspend 한다.
 *          - 결과가 이미 준비되어 있으면 즉시 값을 반환한다.
 *          - 즉, thread 를 block 하는 것이 아니라 현재 코루틴만 잠시 멈춘다.
 */
fun main(): Unit = runBlocking {

    myPrint("runBlocking start")

    val deferred = async {
        myPrint("async start")
        3 + 5
    }

    myPrint("before await")

    val result = deferred.await()

    myPrint("result: $result")
    myPrint("runBlocking end")
}

/**
 * 실행 결과
 *      runBlocking start
 *      before await
 *      async start
 *      result: 8
 *      runBlocking end
 *
 * 설명
 *      - async 는 새로운 자식 코루틴을 생성하고 Deferred 를 반환한다.
 *      - 반환된 Deferred 는 아직 계산이 끝나지 않았을 수도 있는 결과를 들고 있다.
 *      - await() 를 호출하면 결과가 준비될 때까지 현재 코루틴이 suspend 된다.
 *      - 이 예제에서는 계산이 매우 짧아서 바로 결과를 받을 수 있지만,
 *        실제로는 네트워크 요청이나 여러 비동기 계산 결과를 합칠 때 자주 사용한다.
 *
 * 핵심
 *      - async 는 "결과가 필요한 비동기 작업"에 사용한다.
 *      - 결과를 사용할 때는 await() 로 값을 꺼낸다.
 *      - await() 는 blocking 이 아니라 suspend 이다.
 */
