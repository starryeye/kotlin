package sub5_type_casting.instance_of

class KotlinTypeCasting {

    fun printAgeIfPerson11(o: Any) {
        if (o is Person) {
            val person = o as Person
            println(person.age)
        }
    }

    fun printAgeIfPerson12(o: Any) {
        if (o is Person) {
            println(o.age)
        }
    }

    fun printAgeIfPerson2(o: Any) {
        if (o !is Person) {
            // do something..
        }
    }

    class Person(val age: Int) {}
}