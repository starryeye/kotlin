package sub1_variable

/**
 * 코틀린의 변수에 대해서 알아본다.
 */

var x = 5 // top 레벨에 위치한 변수이다. 코틀린에서는 변수를 클래스, 함수 외부에 위치 시킬 수 있다.

fun main() {

    val a : Int = 1 // 변수명, 타입, 값

    val b = 1 // 컴파일러가 타입 추론을 할 수 있다.

    //-----------------------------------------------------------------

    // 지연 할당
    val c : Int
    c = 3

    // 지연 할당에서는 타입 추론이 불가능하여 타입을 반드시 선언해줘야한다.
//    val d //compile error
//    d = 123

    //-----------------------------------------------------------------

    /**
     * 변수 선언 키워드 2가지
     * 1. val (value)
     * java 의 final 키워드와 같이 변수에 한번 값을 초기화 하면 재할당 불가능
     *
     * 2. var (variable)
     * 가변 변수로 재할당 가능하다.
     */
    var e : String = "Hello"
    e = "World" //val 이면 compile error

    var f = 123
//    f = "hi" // 한번 타입이 정해지면 타입이 변경되지 않는다. compile error

    //-----------------------------------------------------------------

    // 탑레벨에 위치한 변수를 사용
    x += 1
    println(x)
}