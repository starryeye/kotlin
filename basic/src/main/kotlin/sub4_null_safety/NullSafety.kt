package sub4_null_safety

/**
 * 코틀린에서는 null 가능성(nullable) 을 컴파일러가 감지해준다.
 */

fun main() {

    // value 에 null 로 초기화 하면 compile error
//    val a : String = null

    // variable 에도 null 을 할당하면 compile error
//    var b : String = "bb"
//    b = null
//    b = "dd"

    // --------------------------------------------

    // 사실 코틀린에는 nullable 한 타입이 따로 존재한다.
    // 위에서 String 타입은 null 을 허용하지 않는다.
    var c : String? = null // nullable Type

    // 현재 변수 c 는 nullable 한 변수이므로 참조를 사용하면 complie error
//    c.length

    // nullable 한 변수의 참조는 안전 연산자 "?" 를 붙이면 사용할 수 있다.
    c?.length

    // 안전 연산자가 붙은 변수인데 null 참조가 이루어지면 null 을 리턴한다. (NPE 발생하지 않음)
    // 즉, c 가 null 이면 length 를 동작시키지 않고 그대로 리턴함.
    println("c?.length : " + c?.length)

    // ---------------------------------------------

    var d : String? = null

    // nullable 변수와 if-else 를 사용한 케이스
    val e : Int = if(d != null) d.length else 0
    println("d : $d")

    // 엘비스 연산자 "?:" , 좌변이 null 일 경우 우변을 리턴한다.
    val f = d?.length ?: 1
    println("f : $f")
}