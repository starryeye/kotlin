package sub7_inheritance

abstract class A {

    abstract val age:Int
    abstract fun hello(string:String)
}

class B(override val age: Int = 20) : A() {

    override fun hello(string: String) {
        println(string)
    }
}

fun main() {

    var b = B()

    b.hello("kotlin")
    println(b.age)
}