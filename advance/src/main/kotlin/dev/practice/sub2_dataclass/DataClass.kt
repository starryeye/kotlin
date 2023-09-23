package dev.practice.sub2_dataclass

/**
 * 코틀린에서 data class 에 대해 알아본다.
 * - decomplie 기능을 통해 내부를 알아보자..
 * - Intellij > Tools > Kotlin > Show Kotlin Bytecode > Decompile 버튼
 * - 코들린으로 작성된 코드가 자바코드로는 어떻게 되는지.. 알아볼 수 있음.
 *
 * 주로 dto 에 사용된다.
 *
 * Java 의 record 와 비슷함. 불변 객체
 *
 * equals, hashcode, toString, componentN, copy 등의 함수를 컴파일러가 자동으로 생성해준다.
 */
data class PersonOfDataClass(val name: String, val age: Int)

class PersonOfClass(val name: String, val age: Int)

fun main() {

    //-------------------------------------------

    //일반 클래스

    val personOfClass1 = PersonOfClass(name = "class", age = 10)
    val personOfClass2 = PersonOfClass(name = "class", age = 10)

    println("일반 클래스에서의 동등성 : ${personOfClass1 == personOfClass2}")

    println("일반 클래스 toString 은 정의가 되어있지 않아서 참조값이 출력됨 : ${personOfClass1.toString()}")


    //-------------------------------------------

    //data 클래스

    val personOfDataClass1 = PersonOfDataClass(name = "data class", age = 20)
    val personOfDataClass2 = PersonOfDataClass(name = "data class", age = 20)

    println("data 클래스에서의 동등성 : ${personOfDataClass1 == personOfDataClass2}")
    val set = hashSetOf(personOfDataClass1)
    println("data 클래스에서는 동등하면 동일하므로 해시 값이 동일 하다. ${set.contains(personOfDataClass2)}")

    println("data 클래스 toString 은 정의가 되어있음 : ${personOfDataClass1.toString()}")
}