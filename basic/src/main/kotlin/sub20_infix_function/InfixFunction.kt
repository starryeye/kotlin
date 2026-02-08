package sub20_infix_function

fun main() {

    /**
     * infix(중위) 함수
     *      함수 호출 방식을 바꿔준다. (ex. downTo, step, to..)
     *      변수.함수이름(args) 대신..
     *          변수 함수이름 args 로 호출 가능..
     */

    val str = "AA"
    println(str repeatStr 3)
    println(str.repeatStr(3)) // 위와 동일
}

/**
 * infix fun 으로 infix 함수로 선언함.
 * 문자열을 지정한 횟수만큼 반복해서 반환한다.
 *
 * 사용 예:
 *   "hi" repeat 3  // "hihihi"
 */
infix fun String.repeatStr(times: Int): String {
    require(times >= 0) { "times must be >= 0" }
    return this.repeat(times) // 여기에 사용된 repeat 현재 확장 함수가 아님.
}