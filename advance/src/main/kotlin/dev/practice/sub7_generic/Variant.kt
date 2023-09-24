package dev.practice.sub7_generic

/**
 * 변성 (Variant)
 * - 제네릭의 타입 인자가 하위 타입 및 상위 타입과 어떻게 관련되는지를 지정하는 것이다.
 *
 * 변성의 종류 3가지
 * 1. 공변성 (Covariant) : 자식 타입을 가진 객체는 부모 타입의 객체로 간주될 수 있음
 * 2. 반공변성 (Contravariant) : 부모 타입을 가진 객체는 자식 타입의 객체로 간주될 수 있음.
 * 3. 무공변성 (Invariant) : 타입이 정확히 일치해야 함. (제네릭을 사용하면 기본 속성이다.!!) -> 이때 다형성을 생각하면 안된다.
 *
 * <참고>
 * val str: String = "Hello"
 * val seq: CharSequence = str  // 다형성에 의해 가능
 * val comp: Comparable<String> = str // 다형성에 의해 가능
 *
 * val list1: List<String> = listOf("Hello")
 * val list2: List<CharSequence> = list1  // 컴파일 오류
 *
 *
 *
 * 이팩티브 자바에서 공변성과 반공변성 중 어떤것을 선택해야하는지 규칙
 * -> PECS (Producer -> Extends, Consumer -> Super)
 *
 * 자바 제네릭에서 공변성은 extends, 코틀린에서는 out
 * 자바 제네릭에서 반공변성은 super, 코틀린에서는 in
 *
 * 말이 참 어려운데 그냥 코드 보면 "아 이거구나" 알 수 있다.
 *
 */

class MyVariant<T>(val t: T) {

}

class MyCovariant<out T>(val t: T) { // 공변성 out, T의 하위 타입도 허용됨

}

class MyContravariant<T> {

    fun saveAll(
        to: MutableList<in T>,  // 반공변성 in, T의 상위 타입도 허용됨
        from: MutableList<T>
    ) {
        to.addAll(from)
    }
}

fun main() {

    // ----------------------------------------------------------------------------------
    // 공변성(Covariant)

    val myVariant = MyVariant<String>("AAA")

    //String 클래스는 Comparable, CharSequence 를 상속 받는다.
//    val charSequence: MyVariant<CharSequence> = myVariant //compile error, 제네릭은 기본적으로 무공변성이므로

    // myCovariant 는 String 으로 제네릭이 정해졌다.
    val myCovariant = MyCovariant<String>("AAA")

    val charSequence: MyCovariant<CharSequence> = myCovariant // 공변성을 이용하여 가능해짐.
    // CharSequence 로 제네릭이 정해졌는데 그 하위 타입도 허용한다. myCovariant 는 CharSequence 하위 타입이므로 허용됨


    // ----------------------------------------------------------------------------------
    // 반공변성(Contravariant)

    // myContravariant 는 String 으로 제네릭이 정해졌다.
    val myContravariant = MyContravariant<String>()
    // 그런데 String 의 상위 타입(CharSequence) 도 허용이 필요해졌다. 따라서 반공변성을 사용한다.
    myContravariant.saveAll(mutableListOf<CharSequence>("a", "b"), mutableListOf<String>("c", "d"))
}