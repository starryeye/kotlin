package dev.practice.sub6_extensionfunction

/**
 * 코틀린의 확장 함수에 대해 알아본다.
 * 기존에 있던 객체에 함수를 추가하는 기능이다.
 *
 * 자바에선 데코레이터 패턴 등과 같이 상속을 통해서만 기존 객체에 기능을 추가할 수 있다..
 *
 * 코틀린에서는 새로운 마법이 있는 건 아니지만..
 * 사실.. 새로운 객체를 만들고 거기에 String 객체를 파라미터로 받는 static 메서드를 만들어주는 방식이다.
 * -> decompile 참고해보자..
 */

//String 객체에 first 라는 함수를 추가하였다.
fun String.first() : Char {
    return this[0] // this 는 확장의 대상이 되는 객체의 참조이다. (receiver, 수신자 객체라 불림)
}

//String 객체에 addFirst 라는 함수를 추가하였다.
fun String.addFirst(char: Char) : String {
    return char + this.substring(0)
}

class MyExample {
    fun printMessage() = println("AAA")
}
fun MyExample.printMessage() {
    println("BBB")
}

fun MyExample?.printNullOrNotNull() {
    if (this == null) {
        println("null")
    } else {
        println("not null")
    }
}

fun main() {

    //확장 함수 사용
    println("abc".first())
    println("abc".addFirst('z'))

    // 기존 함수와 확장함수의 시그니처가 동일 할 경우 기존함수가 우선이다.
    MyExample().printMessage()

    //확장 함수 내에서 null 체크를 하고 있는 것을 컴파일러는 알고 있다.
    var myExample: MyExample? = null //안전 연산자를 통해 nullable 한 타입을 사용하였다.

    //원래 여기서 myExample?.printNullOrNotNull 이렇게 사용해야하지만.. 확장함수 내에서 null 체크를 하고 있는것을 컴파일러가 알고 있다.
    myExample.printNullOrNotNull() // 따라서, 안전연산자 사용안해도 됨.
}