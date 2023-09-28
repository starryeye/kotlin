package sub2_exception

/**
 * 자바에서 발생한 checked exception 을 코틀린에서 처리해본다.
 */

fun main() {

    val javaThrow = JavaThrow()
    javaThrow.throwCheckedException() // 코틀린에서는 checked exception 처리를 강제하지 않는다.
}