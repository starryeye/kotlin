package sub3_flow_control

/**
 * 코틀린의 while 문에 대해 알아보겠다.
 */

fun main() {

    // 조건을 확인하고 참이면 코드 블럭을 실행하고 다시 조건을 확인하고 코드 블럭 실행하고....

    var x = 5

    while (x > 0) {
        println(x)
        x--
    }
}