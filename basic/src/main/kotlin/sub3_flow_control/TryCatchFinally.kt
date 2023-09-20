package sub3_flow_control

/**
 * 자바에서의 try-catch 는 구문이다.
 * 코틀린에서의 try-catch 는 표현식이다. 따라서 리턴 값이 존재할 수 있다.
 *
 */

fun main() {

    val a = try {
        "1".toInt() // a 변수에 할당된다.
//        "A".toInt()
    }catch (e : Exception) {
        println("예외 발생")
    }

    println(a) // 예외가 발생한다면.. a 에는 Unit 이 할당된다.
}