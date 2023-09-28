package sub1_gettersetter

/**
 * 코틀린에서 자바 클래스를 사용해본다.
 */
fun main() {

    val person = Person()

    // 자바 클래스 메서드 선언 그대로 사용할 수 도 있지만..
    person.getAge()
    person.setAge(10)

    // getter, setter 가 모두 선언 되어 있을 경우..
    // 코틀린 스타일로 사용가능하다.
    person.age = 10
    println(person.age)

    // Person 에서는 UUID 라는 프로퍼티는 없으나 getUUID 라는 메서드가 있다.
    // 마치 프로퍼티가 선언되어있는것 처럼 사용할 수 있다. (물론 getter 만 동작)
    println(person.uuid)

    // Person 에서는 myAddress 메서드로 getter 를 대신하고 있고 setAddress 는 존재한다..
    // 코틀린 스타일로 setter getter 사용불가
    // -> 즉, 코틀린 스타일로 사용하기 위해서는 getter 가 정상적으로 되어있어야 함을 알 수 있다.
//    person.address = "seoul" // compile error
//    println(person.address) // compile error
    person.setAddress("seoul")
    println(person.myAddress())
}