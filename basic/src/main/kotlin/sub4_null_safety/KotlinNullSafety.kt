package sub4_null_safety

/**
 * JavaNullSafety 의 코틀린 버전이다.
 */

// 리턴 타입으로 안전연산자를 통해, nullable String Type 을 주었다.
// 탑레벨, 표현식 스타일로 함수를 작성, 리턴 값은 null
fun getNullStr() : String? = null

// 파라미터에 안전연산자를 통해 nullable String Type 을 주었다.
// 탑레벨, 표현식 스타일로 함수를 작성, 리턴 값도 안전연산자를 사용하였다.
// 널 참조가 이루어지면 NPE 를 발생시키지 않고 null 을 그대로 리턴한다.
// 엘비스 연산자를 이용하여 null 일 경우 0 을 리턴하도록 한다.
// 리턴 타입은 컴파일러에 의해 추론된다.
fun getLengthIfNotNull(str: String?) = str?.length ?: 0

fun main() {
    val nullable = getNullStr()

    println(nullable?.length ?: "null 이다.".length)

    println(getLengthIfNotNull(null))
}