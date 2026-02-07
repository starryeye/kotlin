package sub8_class.ex1_property_and_primary_constructor

fun main() {

    val kotlinPerson = KotlinPerson2(
        name = "Kotlin",
        age = 20
    )

    /**
     * 코틀린에서는 인스턴스.프로퍼티명으로 getter, setter 호출이 된다.
     */
    println("name is ${kotlinPerson.name} and age is ${kotlinPerson.age}") // getter 호출됨.
    kotlinPerson.age = 30
    println("age is ${kotlinPerson.age}") // setter 호출됨.



    /**
     * 코틀린에서 자바 클래스를 다룰때에도 인스턴스.프로퍼티명으로 getter, setter 호출이 된다.
     */
    val javaPerson = JavaPerson("Java", 20)
    println("name is ${javaPerson.name} and age is ${javaPerson.age}") // getter 호출됨.
    javaPerson.age = 30
    println("age is ${javaPerson.age}") // setter 호출됨.
}