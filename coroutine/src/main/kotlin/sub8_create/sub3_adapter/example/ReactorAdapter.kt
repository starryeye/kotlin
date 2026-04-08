package sub8_create.sub3_adapter.example

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import util.myPrint
import java.time.Duration
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Reactor (Mono / Flux) 를 코루틴으로 감싸는 어댑터 예제.
 *
 * 배경
 *      - Spring WebFlux / R2DBC / 일부 외부 라이브러리는 Reactor 의 Mono / Flux 를 반환한다.
 *      - flatMap / map / zip 등의 reactive operator 로 비동기 흐름을 표현하는 방식이다.
 *      - Reactor 의 표현력은 강력하지만, 분기와 예외 처리가 늘어나면 코드가 체이닝 중심으로 흐른다.
 *
 * 코루틴이 해주는 일
 *      - Mono<T> 를 suspend 함수로 감싸면, 호출부에서는 동기 코드처럼 결과를 받아 사용할 수 있다.
 *      - Flux<T> 는 코루틴의 Flow<T> 로 변환해서 collect 할 수 있다.
 *      - 즉, Reactor 기반 라이브러리를 그대로 두고도 호출부만 코루틴으로 옮길 수 있다.
 *      - 기존 자산을 버리지 않고 점진적으로 코루틴으로 이주할 때 매우 유용하다.
 *
 * 이 파일에서 보여주는 것
 *      1. 직접 만든 어댑터: myAwaitSingle()
 *         - kotlinx-coroutines-reactor 의 .awaitSingle() 이 내부적으로 무엇을 하는지 손으로 구현해본다.
 *         - 결국 Mono.subscribe { ... } 의 콜백 안에서 cancellableContinuation.resume / resumeWithException 을 부르는 것이 전부다.
 *      2. 라이브러리 제공 어댑터: kotlinx.coroutines.reactor.awaitSingle
 *         - 우리가 만든 myAwaitSingle() 과 같은 일을 한다.
 *      3. Flux -> Flow 변환
 *         - Flux 는 단일 값이 아니라 여러 값을 발행하므로 suspend "단일 값" 이 아니라 Flow 로 매핑된다.
 *         - .asFlow() 로 한 줄 변환이 가능하다.
 *
 * 주의
 *      - 이 파일을 실행하려면 build.gradle 의 의존성에 다음이 있어야 한다.
 *          implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.10.2")
 *          implementation("io.projectreactor:reactor-core:3.6.10")
 */

/**
 * Mono<T> 를 suspend 함수로 변환하는 어댑터 (직접 구현 버전).
 *
 * 동작 원리
 *      1. suspendCancellableCoroutine 으로 코루틴을 suspend 시키고 cancellableContinuation 를 받는다.
 *      2. mono.subscribe(onNext, onError) 안에서
 *         - 값이 도착하면 cancellableContinuation.resume(value)
 *         - 에러가 도착하면 cancellableContinuation.resumeWithException(error)
 *      3. invokeOnCancellation 으로 코루틴이 취소되면 Subscription 도 dispose 한다.
 *
 * 즉, "Reactor 의 발행 시점" 과 "코루틴의 재개 시점" 을 잇는 다리 역할이다.
 * sub5_resume_mechanism 에서 본 Continuation 의 활용 예 그 자체다.
 */
suspend fun <T : Any> Mono<T>.myAwaitSingle(): T =
    suspendCancellableCoroutine { cancellableContinuation ->
        val disposable = subscribe(
            { value -> cancellableContinuation.resume(value) },
            { error -> cancellableContinuation.resumeWithException(error) },
        )
        cancellableContinuation.invokeOnCancellation {
            disposable.dispose()
        }
    }

/**
 * 0.5초 뒤에 사용자 이름을 발행하는 가짜 비동기 API.
 * 실제로는 R2DBC 조회 / WebClient 호출이라고 생각하면 된다.
 *
 * Mono 를 반환
 */
private fun fetchUserNameMono(userId: Long): Mono<String> =
    Mono.just("user-$userId").delayElement(Duration.ofMillis(500))

/**
 * 사용자 이름을 받아서 환영 문구를 발행하는 가짜 비동기 API.
 *
 * Mono 를 반환
 */
private fun greetMono(name: String): Mono<String> =
    Mono.just("Hello, $name!").delayElement(Duration.ofMillis(500))

/**
 * 여러 값을 발행하는 가짜 비동기 스트림.
 *
 * Flux 를 반환
 */
private fun fetchEventsFlux(): Flux<String> =
    Flux.just("event-1", "event-2", "event-3")
        .delayElements(Duration.ofMillis(200))

fun main(): Unit = runBlocking {

    // case 1) "Reactor 스타일" - flatMap 체이닝
    //      - Mono 끼리 flatMap 으로 이어붙이고, block() 으로 결과를 기다린다.
    //      - block() 은 데모 목적이며, 실무에서는 사용하면 안 된다.
    val reactorStyleStart = System.currentTimeMillis()
    val reactorStyleResult = fetchUserNameMono(1)
        .flatMap { name -> greetMono(name) }
        .block()
    val reactorStyleElapsed = System.currentTimeMillis() - reactorStyleStart
    myPrint("reactor style    : $reactorStyleResult (elapsed=${reactorStyleElapsed}ms)")


    // case 2) 직접 만든 어댑터 myAwaitSingle() 사용
    //      - Mono 는 그대로 두고, 호출부만 동기 코드처럼 작성된다.
    val mineStart = System.currentTimeMillis()
    val name = fetchUserNameMono(2).myAwaitSingle()
    val greet = greetMono(name).myAwaitSingle()
    val mineElapsed = System.currentTimeMillis() - mineStart
    myPrint("awaitSingleMine  : $greet (elapsed=${mineElapsed}ms)")


    // case 3) 라이브러리 제공 .awaitSingle() 사용
    //      - kotlinx-coroutines-reactor 의 awaitSingle() 은 awaitSingleMine() 과 같은 일을 한다.
    val libStart = System.currentTimeMillis()
    val name2 = fetchUserNameMono(3).awaitSingle()
    val greet2 = greetMono(name2).awaitSingle()
    val libElapsed = System.currentTimeMillis() - libStart
    myPrint("library awaitSingle: $greet2 (elapsed=${libElapsed}ms)")


    // case 4) 예외 처리도 try-catch 로 자연스럽게 작성된다.
    val failingMono: Mono<String> = Mono.error(IllegalStateException("외부 호출 실패"))
    try {
        failingMono.myAwaitSingle()
    } catch (e: IllegalStateException) {
        myPrint("예외 잡음: ${e.message}")
    }


    // case 5) Flux -> Flow 변환
    //      - 단일 값이 아닌 스트림은 Flow 로 받는 것이 자연스럽다.
    //      - .asFlow() 한 줄로 Reactor Flux 가 코루틴 Flow 가 된다.
    //      - 호출부는 collect / toList 같은 익숙한 코루틴 연산으로 다룰 수 있다.
    val events = fetchEventsFlux().asFlow().toList()
    myPrint("flux -> flow    : $events")
}

/**
 * 실행 결과 예시
 *      [main] reactor style    : Hello, user-1! (elapsed=약 1000ms)
 *      [main] myAwaitSingle  : Hello, user-2! (elapsed=약 1000ms)
 *      [main] library awaitSingle: Hello, user-3! (elapsed=약 1000ms)
 *      [main] 예외 잡음: 외부 호출 실패
 *      [main] flux -> flow    : [event-1, event-2, event-3]
 *
 *      ※ awaitSingle 이후 결과 출력 스레드는 Reactor 의 parallel scheduler 일 수도 있다.
 *        runBlocking 의 dispatcher / Reactor scheduler 의 상호작용에 따라 [main] 이 아닌
 *        [parallel-...] 같은 스레드 이름이 보일 수 있다.
 *
 * 핵심 포인트
 *      - 세 가지 방식 모두 동작은 같다.
 *      - 차이는 "코드를 어떻게 읽게 되는가" 에 있다.
 *
 *      Reactor 스타일 (체이닝)
 *          fetchUserNameMono(1)
 *              .flatMap { name -> greetMono(name) }
 *              .map { ... }
 *              .onErrorResume { ... }
 *
 *      Coroutine 스타일 (어댑터 + suspend)
 *          val name  = fetchUserNameMono(1).awaitSingle()
 *          val greet = greetMono(name).awaitSingle()
 *          // try-catch 로 예외 처리
 *
 * 정리
 *      - 코루틴은 Reactor 를 대체하기 위한 도구가 아니라,
 *        Reactor 같은 reactive 라이브러리의 결과를 동기 코드처럼 소비할 수 있게 해주는 어댑터 계층으로도 쓸 수 있다.
 *      - 단일 값은 .awaitSingle() / .awaitSingleOrNull() 로,
 *        스트림은 .asFlow() 로 코루틴 세계로 가져온다.
 *      - 이렇게 하면 Spring WebFlux 같은 reactive 기반 코드 위에서도
 *        suspend / Flow 중심의 깔끔한 코드를 작성할 수 있다.
 *      - 단, blocking 작업은 여전히 blocking 이라는 점은 동일하다 — 어댑터는 표현 방식만 바꾼다.
 */
