package sub27_component_n

fun main() {

    val person1 = Person1("A", 10)
    val person2 = Person2("B", 10)

    /**
     * Kotlin 의 componentN
     * person 객체의 프로퍼티 접근시..
     * 아래와 같이 접근이 가능하다.
     */
    val name1 = person1.component1() // person 객체의 첫번째 프로퍼티(name) 접근
    val age1 = person1.component2() // person 객체의 두번째 프로퍼티(age) 접근
    println("$name1, $age1")


    /**
     * 구조분해 문법(destructuring declaration)..
     * componentN 함수를 사용한 Kotlin 의 특별한 문법 설탕임.
     *
     * val (name2, age2) = person2
     * 위 문법은
     * val name2 = person2.component1()
     * val age2 = person2.component2()
     * 와 동일하다.
     */
    val (name2, age2) = person2 // 구조분해 문법을 이용해 변수를 한번에 초기화
    println("$name2, $age2")
}

/**
 * data class 는 기본적으로 componentN 함수를 만들어준다.
 */
data class Person1(val name: String, val age: Int)

/**
 * 일반 class 에서 componentN 을 사용하려면 직접 만들어줘야함.
 */
class Person2(val name: String, val age: Int) {
    operator fun component1() = name
    operator fun component2() = age
}

/**
 * 왜 componentN 함수의 operator 키워드는 무엇인가..
 *
 * operator 는
 *  "이 함수는 Kotlin의 특수 문법(연산자/구조분해 문법)에 의해
 *   암묵적으로 호출될 수 있다"
 * 라는 것을 컴파일러에게 명시적으로 알려주는 키워드다.
 *
 *
 * operator 는 Kotlin 전용 문법 참여 허용 표식이다
 *      이 함수는 Kotlin의 연산자/특수 문법에 참여할 수 있다
 * operator 키워드는 성능 최적화나 특별한 로직을 의미하지 않는다.
 *
 *
 * 구조분해 외에도 operator 가 필요한 경우들
 *   operator fun plus(...)        // a + b
 *   operator fun get(index)       // a[index]
 *   operator fun set(index, v)    // a[index] = v
 *   operator fun invoke()         // a()
 *   operator fun compareTo(...)   // a < b
 */
