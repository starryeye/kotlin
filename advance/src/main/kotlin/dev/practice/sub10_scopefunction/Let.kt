package dev.practice.sub10_scopefunction

/**
 * [2]
 *
 * let
 * - it 으로 수신자 객체 참조, 함수의 마지막 코드 줄의 값이 반환 값, 확장 함수로 구현됨
 *
 * null 이 아닌 경우 사용될 로직을 let 스코프 함수 내부 로직으로 작성하고..
 * 새로운 결과를 반환하고 싶을 때 사용한다.
 *
 */

fun main() {

    // str 이라고 하는 nullable 변수에 null 을 참조로 넣었다.
    val str: String? = null

    // let 스코프 함수 사용
    str?.let { // 현재 str(수신자 객체) 은 null 이므로 let 은 실행되지 않는다..
        println("str $it") // it 으로 str 을 참조
    }

    // ---------------------------------------------------------

    val str2: String? = "A"

    str2?.let {// str 은 null 이 아니므로 let 이 실행된다.
        println("str2 $it")
    }

    // ---------------------------------------------------------

    val str3: String = "B"

    str3.let {// str3 은 nullable 변수가 아니므로 이 경우 let 은 무조건 실행된다.
        println("str3 $it")
    }
    // IDE 에서 println(str3) 으로 쓰라고 가이드된다.


    // ---------------------------------------------------------

    val str4: String? = "B"

    val let: Unit? = str4?.let {// let 은 반환값이 존재한다.
        println("str4 $it")
    }

    println("str4 $let") // let 스코프 함수의 마지막 줄인 println("str4 $it") 의 반환 값이 반환된다. (Unit?)


    // ---------------------------------------------------------

    val str5: String? = null

    val let2 = str5?.let {// let 은 반환값이 존재하는데 let 자체가 실행되지 않으므로.. null 이 반환된다.
        println("str5 $it")
    }

    println("str5 $let2")

}