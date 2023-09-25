package dev.practice.sub10_scopefunction

/**
 * [4]
 *
 * with
 * - this 로 수신자 객체 참조, 함수의 마지막 코드 줄의 값이 반환 값, 확장 함수로 구현되지 않음
 *
 * 결과 반환 없이 내부에서 수신자 객체를 이용하여 다른 함수를 호출하고 싶을 때 사용된다.
 *
 * with 스코프 함수는 파라미터로 수신자 객체와 코드 블럭을 받는다.
 * 받은 수신자 객체로 run 과 동일한 동작을 하는 것 처럼 보인다.
 * -> 파라미터로 수신자 객체를 받기 때문에 확장 함수가 아닌 것이다.
 *
 * 결국 run 과 with 의 차이는
 * 확장함수로 사용할 것인가.. 수신자 객체를 직접 넣어줄 것인가.. 정도의 차이다.
 *
 */

fun main() {
    val str = "12345"

    // with 스코프 함수 사용
    with(str) {// 파라미터로 수신자 객체를 받는다.
        println("length = $length") // 수신자 객체 참조로 this 가 이용되며 this 는 생략 가능
    }

    val result: Int = with(str) {
        this.length + 1
        this.length // with 스코프 함수는 코드 블럭 마지막 줄 결과를 반환 한다.
    }
    println(result)
}