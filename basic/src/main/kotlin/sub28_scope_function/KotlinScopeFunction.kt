package sub28_scope_function

/**
 * Kotlin 의 scope function..
 *
 * scope function
 *      일시적인 영역을 형성하는 함수.
 *      일종의 확장함수이다.
 *      람다를 사용하여 일시적인 영역을 만들고, method chaining 에 활용하는 함수이다.
 *
 * 대표 scope function
 *      let
 *          확장함수
 *          람다의 결과 반환
 *          수신 객체 참조시 it 을 사용
 *              "it." 으로 참조하는 대신, "(원하는참조명) -> (원하는참조명)." 으로 참조 가능
 *              this 는 외부 스코프의 참조가 된다.
 *      run
 *          확장함수
 *          람다의 결과 반환
 *          수신 객체 참조시 this 를 사용
 *              리시버가 T.() 로 T 에 대한 확장함수이므로 람다내부에서 "this." 을 생략가능 (sub19_extension_function 특징4 참고)
 *              외부 스코프의 참조가 가려짐
 *      also
 *          확장함수
 *          람다 결과와 무관하게 객체 그자체를 반환
 *          수신 객체 참조시 it 을 사용
 *              "it." 으로 참조하는 대신, "(원하는참조명) -> (원하는참조명)." 으로 참조 가능
 *              this 는 외부 스코프의 참조가 된다.
 *      apply
 *          확장함수
 *          람다 결과와 무관하게 객체 그자체를 반환
 *          수신 객체 참조시 this 를 사용
 *              "리시버가 T.() 로 T 에 대한 확장함수이므로 람다내부에서 "this." 을 생략가능 (sub19_extension_function 특징4 참고)
 *              외부 스코프의 참조가 가려짐
 *      with
 *          확장함수가 아니라 일반 함수이다.
 *          첫 번째 인자로 객체(리시버)를 받음
 *          두번째 인자는 run 과 타입, 역할이 동일함
 *          수신 객체 참조시 this 를 사용
 *              "리시버가 T.() 로 T 에 대한 확장함수이므로 람다내부에서 "this." 을 생략가능 (sub19_extension_function 특징4 참고)
 *
 * 정리..
 *      let / run        : 결과(R)를 반환 (변환/계산)
 *      also / apply     : 리시버(T)를 반환 (side-effect / 설정)
 *      let / also       : it 기반 (명시적)
 *      run / apply / with : this 기반 (문맥)
 *      null-safe 여부는 항상 safe-call(?.)이 결정
 *
 * 시그니처
 *      let
 *          public inline fun <T, R> T.let(block: (T) -> R): R
 *              리시버(T)에 대해 block(람다)을 "항상" 실행하고 그 결과 R 을 반환한다.
 *              사용
 *                  val r = t.let { it.xxx() }
 *              주의
 *                  null-safe 동작은 let 이 아니라 safe-call(?.)이 담당한다.
 *                  예) x?.let { ... }  // x가 null이면 실행 안 하고 null로 평가됨
 *      run
 *          public inline fun <T, R> T.run(block: T.() -> R): R
 *              리시버(T.())에 대해 block(람다)을 "항상" 실행하고 그 결과 R 을 반환한다.
 *                  T 에 대한 확장함수가 파라미터이다.
 *              사용
 *                  val r = t.run { xxx() }
 *      also
 *          public inline fun <T> T.also(block: (T) -> Unit): T
 *              리시버(T)에 대해 block(람다)을 "항상" 실행하고 원래 리시버를 그대로 반환한다.
 *              사용
 *                  val r = t.also { println(it) }
 *      apply
 *          public inline fun <T> T.apply(block: T.() -> Unit): T
 *              리시버(T.())에 대해 block(람다)을 "항상" 실행하고 원래 리시버를 그대로 반환한다.
 *              사용
 *                  val r = t.apply { xxx() }
 *      with
 *          public inline fun <T, R> with(receiver: T, block: T.() -> R): R
 *              첫번째 파라미터(T)가 리시버이며, 두번째 람다는 run 의 람다와 동일
 *              사용
 *                  val r = with(t) { xxx() }
 *      takeIf
 *          public inline fun <T> T.takeIf(predicate: (T) -> kotlin.Boolean): T?
 *              predicate가 true면 this 반환, false면 null 반환
 *      takeUnless
 *          public inline fun <T> T.takeUnless(predicate: (T) -> kotlin.Boolean): T?
 *              predicate가 false면 this 반환, true면 null 반환 (takeIf의 반대)
 *
 */