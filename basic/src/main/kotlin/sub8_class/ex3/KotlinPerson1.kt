package sub8_class.ex3

class KotlinPerson1(val name: String, var age: Int) {

    /**
     * 코틀린에서도 Java 에서 처럼 그대로 메서드를 만들 수 있지만..
     * -> KotlinPerson2 참고
     */
    fun isAdult(): Boolean = this.age >= 20 // Boolean 생략가능
}