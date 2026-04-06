package sub11_coroutine_scope.sub5_structured_concurrency

/**
 * CancellationException..
 *
 *      sub8 에서 cancel(), isActive, CancellationException 을 한 번 보았고,
 *      sub11 에서는 예외 전파와 CoroutineExceptionHandler 를 봤다.
 *
 *      여기서 중요한 질문이 생긴다.
 *          "그러면 CancellationException 은 일반 예외와 왜 다른가?"
 *          "Job 은 내부적으로 어떤 상태를 거치며 종료되는가?"
 *
 * CancellationException 은 왜 특별한가?
 *      Kotlin coroutine 에서 CancellationException 은 "실패"를 표현하는 예외라기보다
 *          "취소(cancellation)"를 표현하는 신호이다.
 *
 *      즉,
 *          IllegalStateException, RuntimeException 같은 예외는 "정상 흐름이 아닌 실패"를 의미한다.
 *          반면 CancellationException 은 "이 코루틴은 취소 절차에 들어간다"는 의미이다.
 *          따라서,
 *              일반 예외(CancellationException 제외한 예외)가 자식에서 발생되면..
 *                  부모를 실패시키고 이후, 부모의 자식들 취소
 *              일반 예외(CancellationException 제외한 예외)가 부모에서 발생되면..
 *                  모든 자식들 취소
 *                  부모가 루트 코루틴이면, CoroutineExceptionHandler 에 의해 처리
 *              CancellationException 예외가 자식에서 발생되면..
 *                  정상 취소로 간주되고, 부모까지 취소시키지 않음
 *              CancellationException 예외가 부모에서 발생되면..
 *                  모든 자식들 취소
 *                  부모가 루트 코루틴이라도 CoroutineExceptionHandler 에 의해 처리되지 않는다.
 *      주의,
 *          위와같은 이유로 try-catch 를 다룰때 CancellationException 은 다시 던져야한다.
 *
 *
 * Structured concurrency..
 * 수많은 코루틴들이 유실되거나 누수되지 않게 보장하는 것을 말한다.
 *
 *      자식 코루틴에서 일반 예외가 발생할 경우, Structured concurrency 에 의해 부모 코루틴이 취소되고
 *          부모 코루틴의 다른 자식 코루틴들도 취소된다.
 *      자식 코루틴에서 예외가 발생하지 않더라도, 부모 코루틴이 취소되면 자식 코루틴도 취소된다.
 *      다만, CancellationException 은 정상적인 취소로 간주하기 때문에 부모 코루틴에 전파되지 않고,
 *          부모 코루틴의 다른 자식 코루틴도 취소시키지 않는다.
 *
 */
fun main() {}
