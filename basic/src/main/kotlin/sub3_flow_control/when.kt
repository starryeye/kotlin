package sub3_flow_control

/**
 * 코틀린의 "when 식" 에 대해 알아본다.
 *
 * Java 의 switch 식 과 비슷하다.
 */

fun main() {

    val day = 2

    val result1 = when (day) {
        1 -> "A"
        2 -> "B"
        3 -> "C"
        4 -> "D"
        5 -> "E"
        else -> "F" // 반환 값이 존재하면 else 가 필수 이다.
    }
    println(result1)

    // --------------------------

    // 반환 값이 존재해도 else 를 생략 할 수있다.
    // enum 값이 2개 뿐이고 when 식에서 2개를 모두 명시 해줬기 때문에 이럴 경우 생략이 가능하다.
    val result2 = when(getColor()) {
        Color.RED -> "red"
        Color.GREEN -> "green"
    }
    println(result2)

    // --------------------------

    // 여러 개의 조건을 콤마로 구분해 한줄로 작성할 수 있다.
    val result3 = when(getNumber()) {
        0, 1 -> println("0 or 1")
        else -> println("is not 0 or 1")
    }
    println(result3) // 반환 값이 없으므로 Unit 이 반환된다.
}

enum class Color {
    RED, GREEN
}

fun getColor() = Color.RED

fun getNumber() = 2