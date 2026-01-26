package sub3_flow_control

/**
 * 코틀린의 for loop 를 알아보겠다.
 */

fun main() {

    /**
     * Kotlin에서 `..` 연산자와 `until` 함수는 범위(Range)를 생성하며,
     * Int의 경우 IntRange 객체를 만든다.
     *
     * IntRange는 IntProgression(등차 수열)을 상속하며,
     * 시작값, 끝값, 증가 간격(step)을 포함한다.
     *
     * `downTo`는 역방향 Progression을 생성하는 확장 함수이고,
     * `step`은 Progression에 대해 간격을 지정하는 함수이다.
     */

    // 범위 연산자 ".." 를 사용하여 for loop
    // 마지막 숫자 포함
    for (i in 0..3) {
        println(".. : $i") //0,1,2,3
    }

    // until 을 사용하여 반복
    // 마지막 숫자는 포함하지 않음
    for (i in 0 until 3) {
        println("until : $i") //0,1,2
    }

    // step 값 만큼 증가 시킬 수 있다.
    for (i in 0..6 step 2) {
        println("step : $i") //0,2,4,6
    }

    // downTo 로 감소 시킬 수 있다.
    for (i in 6 downTo 0 step 2) {
        println("downTo : $i") //6,4,2,0
    }

    // 전달 받은 배열을 반복, Java enhanced for loop
    //      in 뒤에 들어가는 객체가
    //          Java 객체라면 iterable 을 구현한 객체면 가능
    //          Kotlin 객체라면 iterable 을 구현하던가,
    //              operator fun iterator(): Iterator<T> 처럼 iterator() 함수를 제공하면 가능
    val numbers = arrayOf(1, 2, 3)
    for (i in numbers) {
        println("array : $i")
    }

    /**
     * if (y < 0 || y > n) 을..
     * if (y !in 0..n) 으로 표현할 수 있고
     *
     * if (y >= 0 && y <= n) 을..
     * if (y in 0..n) 으로 표현할 수 있다.
     */
}