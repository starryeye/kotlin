package dev.practice.sub7_generic

/**
 * 코틀린에서의 제네릭에 대해 알아본다.
 *
 * 자바와 같이 타입 파라미터를 이용한다.
 */

class MyGeneric<T>(val t: T) {

}

fun main() {
    val myGeneric = MyGeneric<String>("스트링") //타입 파라미터 추론을 할수 있어서 <String> 생략 가능...

    // 변수 타입에 제네릭 선언
    val list1: MutableList<String> = mutableListOf()
    // 생성자에 제네릭 선언
    val list2 = mutableListOf<String>()

    // star projection, 어떤 타입인지 모를때 잠시 사용할 수 있다. 한번 타입이 정해지면 영원히 바꿀수 없다. (아래의 경우 String 으로 정해짐)
    // Any 와 다른 점은.. Any 는 타입이 바뀔수도 있다는 것..
    val list3: MutableList<*> = mutableListOf("eee")
}