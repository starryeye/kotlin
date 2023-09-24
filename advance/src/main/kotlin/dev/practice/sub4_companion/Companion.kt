package dev.practice.sub4_companion

/**
 * 코틀린의 동반객체
 *
 * 클래스 내부에 객체 선언을 할 수 있다.
 *
 * "companion object" 로 선언 가능
 */

class MyClass {
    private constructor() // TODO primary constructor, secondary constructor

    companion object { // 동반객체의 이름은 생략 가능하다.
        val a = 123
        fun newInstance() = MyClass()
    }
}

fun main() {

    println(MyClass.Companion.a) // Companion 은 생략 가능하다.
    println(MyClass.Companion.newInstance()) // Companion 은 생략 가능하다.

    println(MyClass.a) // 동반객체 내의 프로퍼티 참조
    println(MyClass.newInstance()) // 동반객체 내의 함수 참조


}