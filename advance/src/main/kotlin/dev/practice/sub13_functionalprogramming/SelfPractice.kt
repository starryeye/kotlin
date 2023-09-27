package dev.practice.sub13_functionalprogramming

/**
 * [5]
 *
 * 익명함수와 람다 표현식 연습
 */

fun main() {

    //----
    //익명함수
    val printMessage = fun(message: String) { println(message) }
    printMessage("익명 함수")

    (fun(s1: String, s2: String) { println(s1 + s2) }) ("익명", " 함수2")

    val sumMessage: (String, String) -> String = fun(s1: String, s2: String): String { return s1 + s2 } //람다식이 아니므로 리턴을 생략하지 못하는 듯
    println(sumMessage("익명", " 함수3"))

    //----
    //람다식

    val printMessage2 = { message: String -> println(message) }
    printMessage2("람다 식")

    ({ s1: String, s2: String -> println(s1 + s2) }) ("람다", " 식2")


    val sumMessage2 : (String, String) -> String = { s1: String, s2: String -> s1 + s2 }
    println(sumMessage2("람다", " 식3"))

}