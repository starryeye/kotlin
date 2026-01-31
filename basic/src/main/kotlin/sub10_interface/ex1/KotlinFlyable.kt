package sub10_interface.ex1

interface KotlinFlyable {

    fun fly() // Kotlin 의 인터페이스 추상메서드도 Java 와 다를바가 없음

    fun act() { // Java 의 인터페이스 default 메서드는 Kotlin 에서 특별한 키워드 사용필요 없이 이렇게 쓰면됨.
        println("Kotlin Flyable")
    }
}