package dev.practice.sub2_dataclass

/**
 * componentN 에 대해 알아본다.
 *
 * data class 에는 component{숫자} 로 ..
 * 선언된 프로퍼티 개수 만큼 함수가 정의된다.
 *
 * 아래 Member data class 의 경우..
 * name 은 component1, age 는 component2 가 매핑된다.
 */

data class Member(val name: String, val age: Int)

fun main() {

    val member = Member(name = "member", age = 10)
    println("이름 : ${member.component1()}, 나이 : ${member.component2()}")

    //구조분해할당(Destructuring declaration) 문법을 사용가능 (순서는 정확해야함) (실제 자바 코드로는 위와 동일함)
    val (name, age) = member
    println("이름 : $name, 나이 : $age")
}