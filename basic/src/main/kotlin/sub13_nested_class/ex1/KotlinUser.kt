package sub13_nested_class.ex1

class KotlinUser private constructor(
    builder: Builder
) {

    val name: String = builder.name
    val age: Int = builder.age

    /**
     * Kotlin에서 중첩 클래스는 기본이 static 이다.
     * 따라서 Java의 static 중첩 클래스와 동일한 의미를 가진다.
     */
    class Builder {
        /**
         * 중첩 클래스(= static nested class)이므로
         * 외부 클래스(KotlinUser)를 직접 참조할 수 없다.
         */
        var name: String = ""
            private set

        var age: Int = 0
            private set

        fun name(name: String) = apply {
            this.name = name
        }

        fun age(age: Int) = apply {
            this.age = age
        }

        fun build(): KotlinUser {
            return KotlinUser(this) // this는 Builder 인스턴스
        }
    }
}

/**
 * Java 코드를 Kotlin 으로 옮기면 이렇게 되긴하지만..
 * 실제로는 named argument 기능도 있고 해서..
 *
 * 아래와 같이 그냥 data class 로 불변 객체를 만든다.
 *
 * data class User(
 *     val name: String,
 *     val age: Int = 0
 * )
 *
 * 정적 팩토리 메서드를 사용해서 검증로직도 넣고 싶다면..
 *
 * class User private constructor(
 *     val name: String,
 *     val age: Int
 * ) {
 *     companion object {
 *         fun create(name: String, age: Int): User {
 *             require(age >= 0)
 *             return User(name, age)
 *         }
 *     }
 * }
 */

fun main() {
    val user = KotlinUser.Builder()
        .name("Alice")
        .age(20)
        .build()

    println("${user.name}, ${user.age}")
}
