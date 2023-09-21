package sub6_class

/**
 * 코틀린에서는 클래스를 자바와 같이 class 키워드를 사용한다.
 */

// 생성자를 constructor 라는 키워드를 사용하면 만들어줄 수 있다.
// constructor 는 생략 가능
class Coffee constructor(
    val name: String = "americano", // 초기화 가능
    val price: Int, // 콤마를 마지막에도 작성해줄 수 있다.
    var brand: String, // var 사용가능
){

}

class Member( // 생성자이다..
    var name: String = "Kotlin",
    var age: Int = 5,
) // 몸통 부분 생략 가능

class Water(
    var name: String = "samdasoo",
    var price: Int = 1000,
) {

    // val 키워드는 초기화가 필요할텐데 .. getter 가 있으면 되나보다..
    val brand: String
        get() { // getter
            return "물 브랜드"
        }

    var quantity: Int = 0
        set(value) {
            if(value > 0) {
                field = value //field 는 식별자이다. getter, setter 에서 field 를 사용하면 실제 필드를 참조하는 것.
                // 여기서 field 대신에 quauntity 를 사용하면 setter 를 다시한번 호출하게 된다........
                // 그래서 stackOverFlow 발생함.. 무한재귀
            }
        }
}

fun main() {

    val member = Member()

    // 코틀린에서는 var 키워드로 선언되어있으면 자동으로 getter/setter 생성이다.
    // val 키워드이면 getter 만 만들어진다.
    // 아래와 같이 접근함
    // 눈으로 보기엔 프로퍼티를 직접 참조하는 것 처럼 보이지만.. 내부적으로 getter/setter 이다.....
    member.name = "Spring" //setter
    member.age = 20

    println("${member.name} 나이는 ${member.age}") // getter

    val water = Water()
    water.quantity = 3
    println(water.brand)
    println(water.quantity)
}