package sub8_create.sub3_adapter

import kotlinx.coroutines.runBlocking
import util.myPrint
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.suspendCancellableCoroutine

/**
 * 코루틴은 새로운 비동기 런타임이 아니다.
 * 기존의 비동기 API (callback, Future, Mono 등) 를
 * "동기 코드처럼 보이는 suspend 함수"로 감싸주는 어댑터 계층으로도 동작할 수 있다.
 *
 * 그 핵심 도구가 바로
 *      - suspendCoroutine
 *      - suspendCancellableCoroutine
 * 이다.
 *
 * 동작 원리
 *      - suspendCoroutine { cont -> ... } 을 호출하면 현재 코루틴은 suspend 된다.
 *      - 이때 Continuation 객체 (cont) 가 람다 인자로 넘어온다.
 *      - 이 cont 를 외부 비동기 API 의 callback 안에 넘겨두면,
 *        외부 작업이 끝났을 때 cont.resume(value) 를 호출해서 코루틴을 다시 깨울 수 있다.
 *      - 즉, "외부 비동기 작업의 완료 시점" 과 "코루틴의 재개 시점" 을 연결해주는 다리 역할을 한다.
 *
 * sub5_resume_mechanism 과의 연결
 *      - sub5 에서 "코루틴은 누가 깨우는가?" 에 대해 Continuation 을 다루는 외부 주체가 깨운다고 했다.
 *      - 여기서는 그 "외부 주체"가 우리가 직접 작성하는 어댑터 코드라는 점이 다르다.
 *      - 즉, 코루틴 라이브러리 내부의 delay() / await() 와 같은 종류의 일을
 *        우리가 직접 손으로 만드는 셈이다.
 *
 * suspendCoroutine vs suspendCancellableCoroutine
 *      - suspendCoroutine
 *          단순히 Continuation 을 한 번 resume 하면 코루틴을 다시 깨운다.
 *          취소 신호는 전달되지 않는다.
 *      - suspendCancellableCoroutine
 *          코루틴이 취소되면 cont 가 cancel 상태가 되어,
 *          invokeOnCancellation { ... } 으로 등록된 정리 로직이 수행된다.
 *          외부 비동기 작업이 취소 가능한 형태라면 이쪽을 쓰는 것이 안전하다.
 *
 * 이 파일에서 보여주는 것
 *      - "1초 뒤에 콜백을 호출하는" 아주 단순한 비동기 API 를 가정한다.
 *      - 이 API 를 suspend 함수 awaitDelayed() 로 감싼다.
 *      - 호출부에서는 마치 동기 코드처럼 결과를 받아 사용할 수 있다.
 */

/**
 * "콜백 기반의 비동기 API" 를 흉내 낸 함수.
 *      - 호출하면 즉시 반환된다 (non-blocking).
 *      - delayMillis 후에 백그라운드 스레드에서 onResult 콜백을 호출한다.
 *      - 우리가 직접 만든 코루틴 어댑터의 대상이라고 생각하면 된다.
 */
private val scheduler = Executors.newSingleThreadScheduledExecutor { r ->
    Thread(r, "fake-async-api").apply { isDaemon = true }
}

private fun fakeAsyncCall(
    delayMillis: Long,
    onResult: (String) -> Unit,
) {
    scheduler.schedule(
        { onResult("result-after-${delayMillis}ms") },
        delayMillis,
        TimeUnit.MILLISECONDS,
    )
}

/**
 * 위의 callback 기반 API 를 suspend 함수로 감싼 어댑터.
 *
 *      1. suspendCoroutine 으로 현재 코루틴을 suspend 시키고 cont 를 받는다.
 *      2. fakeAsyncCall 의 콜백 안에서 cont.resume(value) 를 호출한다.
 *      3. 콜백이 호출되는 순간 코루틴은 다시 깨어나고, return 된 값처럼 result 를 받는다.
 *
 * 호출부에서는 그냥
 *      val r = awaitDelayed(1000)
 * 처럼 동기 코드처럼 쓸 수 있게 된다.
 */
suspend fun awaitDelayed(delayMillis: Long): String =
    suspendCoroutine { cont ->
        fakeAsyncCall(delayMillis) { result ->
            cont.resume(result)
        }
    }

/**
 * suspendCancellableCoroutine 버전.
 *      - 코루틴이 취소되면 invokeOnCancellation 안에서 정리 로직을 수행할 수 있다.
 *      - 외부 비동기 API 가 취소 가능한 핸들 (Future, Subscription 등) 을 제공한다면
 *        여기서 그 핸들을 cancel 해주는 것이 일반적이다.
 *
 * 아래 예제에서는 fakeAsyncCall 자체에 취소 기능이 없으므로
 * "취소되었을 때 로그를 남기는" 정도의 정리만 한다.
 */
suspend fun awaitDelayedCancellable(delayMillis: Long): String =
    suspendCancellableCoroutine { cont ->
        fakeAsyncCall(delayMillis) { result ->
            // 코루틴이 이미 취소된 경우 resume 을 시도하면 IllegalStateException 이 날 수 있다.
            // isActive 검사 또는 cont.resumeWith 사용으로 안전하게 처리한다.
            if (cont.isActive) {
                cont.resume(result)
            }
        }

        cont.invokeOnCancellation {
            myPrint("awaitDelayedCancellable: 코루틴이 취소되어 정리 로직 수행")
            // 실제 API 라면 여기서 future.cancel(), subscription.dispose() 등을 호출.
        }
    }

/**
 * 실패 케이스도 함께 보여주는 어댑터.
 *      - 외부 작업이 실패하면 cont.resumeWithException(e) 를 호출한다.
 *      - 그러면 호출부에서는 일반 try-catch 로 예외를 받을 수 있다.
 *      - 이것이 "비동기 콜백을 동기 코드처럼" 쓰게 되는 핵심 트릭이다.
 */
suspend fun awaitDelayedOrFail(delayMillis: Long, fail: Boolean): String =
    suspendCoroutine { cont ->
        fakeAsyncCall(delayMillis) { result ->
            if (fail) {
                cont.resumeWithException(IllegalStateException("외부 작업 실패"))
            } else {
                cont.resume(result)
            }
        }
    }

fun main(): Unit = runBlocking {
    myPrint("start")

    // 1) callback 기반 API 가 동기 코드처럼 호출된다.
    val r1 = awaitDelayed(500)
    myPrint("awaitDelayed -> $r1")

    // 2) cancellable 버전도 정상 흐름에서는 동일하게 동작한다.
    val r2 = awaitDelayedCancellable(500)
    myPrint("awaitDelayedCancellable -> $r2")

    // 3) 실패는 try-catch 로 잡을 수 있다.
    try {
        awaitDelayedOrFail(300, fail = true)
    } catch (e: IllegalStateException) {
        myPrint("awaitDelayedOrFail 실패 잡음: ${e.message}")
    }

    myPrint("end")
}

/**
 * 실행 결과 예시
 *      [sub8_create.sub3_adapter.main] start
 *      [sub8_create.sub3_adapter.main] awaitDelayed -> result-after-500ms
 *      [sub8_create.sub3_adapter.main] awaitDelayedCancellable -> result-after-500ms
 *      [sub8_create.sub3_adapter.main] awaitDelayedOrFail 실패 잡음: 외부 작업 실패
 *      [sub8_create.sub3_adapter.main] end
 *
 * 참고
 *      - resume 이 호출되는 시점은 fake-async-api 스레드이지만,
 *        runBlocking 의 기본 dispatcher 가 결국 main 스레드에서 이어 실행하기 때문에
 *        이어지는 코드는 [sub8_create.sub3_adapter.main] 에서 출력된다.
 *      - 즉, 코루틴은 "어디서 깨워졌는가" 와 "어디서 이어 실행되는가" 를 분리할 수 있다.
 *
 * 정리
 *      - suspendCoroutine / suspendCancellableCoroutine 은 코루틴 어댑터의 가장 기본 도구다.
 *      - 외부 비동기 API 가 callback / Future / Publisher 무엇이든,
 *        결국 "완료 시점에 cont.resume / resumeWithException 을 호출"하는 형태로 감쌀 수 있다.
 *      - 이렇게 한번 감싸두면 호출부는 비동기를 의식하지 않고
 *        try-catch 와 순차적 코드 흐름으로 작성할 수 있게 된다.
 *      - 다음 파일에서 이 패턴을 실제 CompletableFuture 와 Reactor Mono 에 적용해본다.
 */
