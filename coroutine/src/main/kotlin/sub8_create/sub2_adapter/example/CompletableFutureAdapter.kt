package sub8_create.sub2_adapter.example

import kotlinx.coroutines.future.await
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import util.myPrint
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * CompletableFuture 를 코루틴으로 감싸는 어댑터 예제.
 *
 * 배경
 *      - Java 진영의 비동기 프로그래밍 표준 중 하나가 CompletableFuture 다.
 *      - thenCompose / thenApply / exceptionally 같은 체이닝으로 비동기 흐름을 표현한다.
 *      - 흐름 자체는 잘 표현되지만, 분기와 예외 처리가 늘어날수록 코드가 읽기 어려워진다.
 *
 * 코루틴이 해주는 일
 *      - CompletableFuture 를 suspend 함수 await() 로 감싸면,
 *        호출부에서는 마치 동기 함수처럼 결과를 받아 사용할 수 있다.
 *      - 즉, 비동기 라이브러리를 버리지 않고도 호출부의 가독성을 크게 높일 수 있는 어댑터 계층이 된다.
 *
 * 이 파일에서 보여주는 것
 *      1. 직접 만든 어댑터: myAwait()
 *         - kotlinx-coroutines-jdk8 의 .await() 가 내부적으로 무엇을 하는지 손으로 구현해본다.
 *         - sub5_resume_mechanism 에서 본 "Continuation 을 외부 작업의 완료 시점에 resume 한다" 의
 *           가장 직관적인 사례다.
 *      2. 라이브러리 제공 어댑터: kotlinx.coroutines.future.await
 *         - 실제로는 우리가 만든 awaitMine() 과 같은 일을 한다.
 *      3. before / after 비교
 *         - 같은 비동기 흐름을 thenCompose 체이닝과 suspend 코드로 각각 작성해본다.
 */

/**
 * CompletableFuture<T> 를 suspend 함수로 변환하는 어댑터.
 *
 * 동작 원리
 *      1. suspendCancellableCoroutine 으로 코루틴을 suspend 시키고 cancellableContinuation 를 받는다.
 *      2. CompletableFuture.whenComplete 콜백 안에서
 *         - 정상 완료면 cancellableContinuation.resume(value)
 *         - 예외 완료면 cancellableContinuation.resumeWithException(error)
 *      3. invokeOnCancellation 으로 코루틴이 취소되면 future 도 cancel 시킨다.
 *
 * 이렇게 한번 감싸면 호출부는 try-catch 와 순차 코드로 비동기 결과를 다룰 수 있다.
 */
suspend fun <T> CompletableFuture<T>.myAwait(): T =
    suspendCancellableCoroutine { cancellableContinuation ->
        whenComplete { value, error ->
            if (error == null) {
                cancellableContinuation.resume(value)
            } else {
                cancellableContinuation.resumeWithException(error)
            }
        }
        cancellableContinuation.invokeOnCancellation {
            cancel(false)
        }
    }

/**
 * 1초 뒤에 사용자 이름을 반환하는 가짜 비동기 API.
 * 실제로는 외부 서비스 호출이라고 생각하면 된다.
 * CompletableFuture 반환
 */
private fun fetchUserNameAsync(userId: Long): CompletableFuture<String> {
    val future = CompletableFuture<String>()
    scheduledExecutor.schedule(
        { future.complete("user-$userId") },
        500,
        TimeUnit.MILLISECONDS,
    )
    return future
}

/**
 * 사용자 이름을 받아서 환영 문구를 만드는 가짜 비동기 API.
 */
private fun greetAsync(name: String): CompletableFuture<String> {
    val future = CompletableFuture<String>()
    scheduledExecutor.schedule(
        { future.complete("Hello, $name!") },
        500,
        TimeUnit.MILLISECONDS,
    )
    return future
}

private val scheduledExecutor = Executors.newSingleThreadScheduledExecutor { r ->
    Thread(r, "fake-cf-executor").apply { isDaemon = true }
}

fun main(): Unit = runBlocking {

    // case 1) "Java 스타일" - CompletableFuture 체이닝
    //      - 흐름은 잘 표현되지만, 단계가 늘어날수록 들여쓰기와 콜백이 깊어진다.
    val javaStyleStart = System.currentTimeMillis()
    val javaStyleResult = fetchUserNameAsync(1)
        .thenCompose { name -> greetAsync(name) }
        .get() // 데모용으로 결과를 기다림 (실무에서는 block 하지 않는다)
    val javaStyleElapsed = System.currentTimeMillis() - javaStyleStart
    myPrint("java style    : $javaStyleResult (elapsed=${javaStyleElapsed}ms)")


    // case 2) 직접 만든 어댑터 myAwait() 사용
    //      - 비동기 API 는 그대로 두고, 호출부만 동기 코드처럼 작성된다.
    val mineStart = System.currentTimeMillis()
    val name = fetchUserNameAsync(2).myAwait()
    val greet = greetAsync(name).myAwait()
    val mineElapsed = System.currentTimeMillis() - mineStart
    myPrint("awaitMine     : $greet (elapsed=${mineElapsed}ms)")


    // case 3) 라이브러리 제공 .await() 사용
    //      - kotlinx-coroutines-jdk8 의 .await() 는 myAwait() 과 같은 일을 한다.
    //      - 차이점은 더 안전하게 구현되어 있다는 정도.
    val libStart = System.currentTimeMillis()
    val name2 = fetchUserNameAsync(3).await()
    val greet2 = greetAsync(name2).await()
    val libElapsed = System.currentTimeMillis() - libStart
    myPrint("library await : $greet2 (elapsed=${libElapsed}ms)")


    // case 4) 예외 처리도 try-catch 로 자연스럽게 작성된다.
    val failingFuture = CompletableFuture<String>().also {
        it.completeExceptionally(IllegalStateException("외부 호출 실패"))
    }
    try {
        failingFuture.myAwait()
    } catch (e: IllegalStateException) {
        myPrint("예외 잡음: ${e.message}")
    }
}

/**
 * 실행 결과 예시
 *      [main] java style    : Hello, user-1! (elapsed=약 1000ms)
 *      [main] awaitMine     : Hello, user-2! (elapsed=약 1000ms)
 *      [main] library await : Hello, user-3! (elapsed=약 1000ms)
 *      [main] 예외 잡음: 외부 호출 실패
 *
 * 핵심 포인트
 *      - 세 가지 방식 모두 동작은 같다.
 *      - 차이는 "코드를 어떻게 읽게 되는가" 에 있다.
 *
 *      Java 스타일 (체이닝)
 *          fetchUserNameAsync(1)
 *              .thenCompose { name -> greetAsync(name) }
 *              .thenApply { ... }
 *              .exceptionally { ... }
 *
 *      Coroutine 스타일 (어댑터 + suspend)
 *          val name  = fetchUserNameAsync(1).await()
 *          val greet = greetAsync(name).await()
 *          // try-catch 로 예외 처리
 *
 *      - 동작은 동일한 비동기 흐름이지만, 분기/예외/반복문이 끼어드는 순간 가독성 차이가 커진다.
 *
 * 중요 - 코루틴이 바꾸는 것
 *      - 코루틴은 기존 비동기 라이브러리를 대체하지 않는다.
 *      - 기존 라이브러리를 그대로 두고, 호출부의 표현 방식만 동기 코드처럼 바꿔준다.
 *      - 즉, 코루틴은 비동기 API 위에 얹는 "어댑터 / DSL 계층" 으로도 쓸 수 있다.
 *
 * 중요 - 코루틴이 바꾸지 않는 것
 *      - 기존 API 가 blocking 이라면 코루틴으로 감싼다고 non-blocking 이 되지 않는다.
 *      - 어댑터는 어디까지나 "완료 시점에 resume 을 호출하는 다리" 일 뿐이다.
 *      - 그래서 blocking API 를 감쌀 때는 Dispatchers.IO 같은 적절한 dispatcher 에서 실행해야 한다.
 */
