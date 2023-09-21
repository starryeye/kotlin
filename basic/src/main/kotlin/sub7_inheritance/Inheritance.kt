package sub7_inheritance

/**
 * 코틀린의 최상위 클래스는 Any 라는 클래스이다.
 *
 * equals, hashCode, toString 함수를 가지고 있다.
 *
 *
 * 코틀린은 기본적으로 자바로 치면 final 클래스로 상속이 막혀있다.
 * 그래서 상속이 필요하다면 open 이라는 키워드를 사용해줘야한다.
 *
 * 참고로
 * override 된 프로퍼티나 함수는 하위 클래스에서 봤을 때 open 상태이다.
 * 이때 하위에서 상속을 막으려면 각각에 final 키워드를 붙여준다.
 */

open class Dog {

    open var name:String = "dog"
    open var age:Int = 0

    open fun bark() {
        println("bark! bark!")
    }
}

// 생성자에서도 오버라이딩이 가능하다.
open class BullDog(override var name: String = "bulldog") : Dog() { // 상속

    //final 을 사용하여 하위 클래스에서 age 는 오버라이딩을 막는다.
    final override var age: Int = 0 // 오버라이딩

    override fun bark() { // 오버라이딩
        println("BARK!!")
    }
}

fun main() {
    val dog = BullDog(name = "BULLDOG")
    println(dog.name)
    dog.age = 3
    println(dog.age)
    dog.bark()
}

