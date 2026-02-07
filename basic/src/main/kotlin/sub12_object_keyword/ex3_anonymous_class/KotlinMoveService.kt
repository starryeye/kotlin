package sub12_object_keyword.ex3_anonymous_class

fun main() {

    /**
     * Kotlin 에서의 익명 클래스..
     *      "object : 타입이름" 으로 사용할 수 있다.
     */

    move(object : KotlinMoveable {
        override fun move() {
            println("kotlin anonymous class")
        }

        override fun fly() {
            println("kotlin anonymous class")
        }

    })
}

private fun move(moveable: KotlinMoveable) {
    moveable.move()
    moveable.fly()
}