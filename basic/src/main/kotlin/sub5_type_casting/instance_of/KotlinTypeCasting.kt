package sub5_type_casting.instance_of

class KotlinTypeCasting {

    fun printAgeIfPerson1(o: Any) {
        if (o is Person) {
            val person = o as Person
            println(person.age)
        }

        // 주석처럼도 가능하다.
//        if (o is Person) {
//            println(o.age) // smart cast
//        }
    }

    fun printAgeIfPerson2(o: Any) {
        if (o !is Person) {
            // do something..
        }
    }

    class Person(val age: Int) {}
}