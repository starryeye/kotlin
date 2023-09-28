package sub3_keyword

/**
 * Java 에서는 문제가 되지 않는 이름이지만..
 * 코틀린에서는 해당 이름이 예약어라서 문제가 되는 경우..
 *
 * 혹은 그냥 코틀린의 예약어를 회피 하고 싶을 경우..
 */

enum class CountryCode {
    kr, jp, us, `do` // do 가 예약어 이므로 "`" 문자로 해결할 수 있다.
}

fun main() {

    val something = Something() // Java 코드

    // in, is 는 코틀린 예약어 이므로.. "`" 문자를 사용하여 구분해준다.
    something.`in`
    something.`is`
}