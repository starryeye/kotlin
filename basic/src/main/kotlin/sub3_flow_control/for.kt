package sub3_flow_control

/**
 * 코틀린의 for loop 를 알아보겠다.
 */

fun main() {

    // 범위 연산자 ".." 를 사용하여 for loop
    // 마지막 숫자 포함
    for (i in 0 .. 3) {
        println(".. : $i") //0,1,2,3
    }

    // until 을 사용하여 반복
    // 마지막 숫자는 포함하지 않음
    for (i in 0 until 3) {
        println("until : $i") //0,1,2
    }

    // step 값 만큼 증가 시킬 수 있다.
    for (i in 0 .. 6 step 2) {
        println("step : $i") //0,2,4,6
    }

    // downTo 로 감소 시킬 수 있다.
    for (i in 6 downTo 0 step 2) {
        println("downTo : $i") //6,4,2,0
    }

    // 전달 받은 배열을 반복, Java forEach
    val numbers = arrayOf(1,2,3)

    for (i in numbers) {
        println("array : $i")
    }
}