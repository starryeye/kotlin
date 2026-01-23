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

    // 코틀린에서는 String 타입과 같이 기존 타입은 null 을 허용하지 않는다.

    // --------------------------------------------

    // nullable type, 안전연산자

    // 코틀린에는 nullable 한 타입이 따로 존재한다.
    var c : String? = null // nullable Type, 기존 타입에 안전연산자를 붙이면 nullable type 이 된다.

    // --------------------------------------------

    // safe call
    // nullable 한 타입을 참조할 때는 "?." 를 사용해야한다. 이를 safe call 이라 부른다.
    // 만약 안전연산자를 사용하지 않는다면, compile error 이다.
//    c.length // compile error

    // safe call에서 내부 값이..
    //      null 이 아니면 정상적으로 호출한다.
    //      null 이면, null 을 리턴한다. (NPE 발생하지 않음)
    println("c?.length : " + c?.length) // "c?.length : null" 이 출력됨.

    // ---------------------------------------------

    var d : String? = null

    // nullable 변수와 if-else 를 사용한 케이스
    val e : Int = if(d != null) d.length else 0
    println("e : $e") // "e : 0" 출력

    // 엘비스 연산자 "?:" , 좌변이 null 일 경우 우변을 리턴한다.
    val f = d?.length ?: 1
    println("f : $f") // "f : 1" 출력

    // ---------------------------------------------

    var str: String? = null // 안전연산자를 사용하여 nullable 타입으로 선언하였다. (실제 값은 상관 없음)
    str?.length // str 은 안전 연산자를 이용하여 nullable 타입 이므로 여기서도 안전연산자를 사용해야한다. (실제 값은 상관 없음)
    str = "a"
    str.length // "a" 로 초기화 해주니까 안전연산자를 사용안해도 되는 모습... ㄷㄷ


    // ---------------------------------------------

    // Java 코드(라이브러리)를 Kotlin 에서 사용할 경우...
    //      코틀린 컴파일러는 annotation 을 이해하고 처리해줌
    //          @Nullable 이 적용된 메서드를 호출하면 안전연산자가 필요함..
    //          @NotNull 이 적용된 메서드를 호출하면 안전연산자가 필요없음..
    //      만약 null 과 관련된 정보가 없다면..
    //          일단 안전연산자가 필요없는데.. 실제로 null 이 들어온다면 NPE 발생한다.

}