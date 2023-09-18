package sub2_function

/**
 * 코틀린의 함수 선언
 *
 * 클래스 외부(탑레벨) 에서 선언 할 수 있다.
 */

// 기본적인 함수 선언 스타일, fun 키워드를 사용한다.
// 순서대로 fun 키워드, 함수명, 파라미터, 반환 타입, 함수 몸통 으로 구성된다.
fun sum(a: Int, b: Int) : Int {
    return a + b
}

// 표현식 스타일, java 의 람다식과 비슷
fun sum2(a: Int, b: Int) : Int = a + b

// 표현식 스타일 + 반환타입 생략, 컴파일러가 반환타입을 추론할 수 있다.
fun sum3(a: Int, b: Int) = a + b

// 함수 몸통이 있고 반환이 존재하는 경우에는 컴파일러가 반환 타입을 추론할 수 없다. compile error
//fun sum4(a: Int, b: Int) {
//    return a + b
//}

// 반환 타입이 없는 함수는 Unit 이라는 타입을 반환한다. (반환 타입으로 Unit 이 생략된 것이다.) 보이드와 비슷
fun printSum(a: Int, b: Int) {
    println("$a + $b = ${a + b}")
}

// default parameter 가능 (java 에서는 맨 마지막 파라미터만 가능..)
fun greeting(name: String = "alice", age: Int = 20) {
    println("name: $name, age: $age")
}

// named argument 기능 제공
fun log(level: String = "INFO", message: String) {
    println("[$level] $message")
}

fun main() {
    printSum(1, 2)

    greeting()
    greeting("bob", 21)

    log(message = "인포 로그")
    log(level = "DEBUG", "디버그 로그")
    log("WARN", "워닝 로그")
    log(level = "ERROR", message = "에러 로그")
    log(message = "트레이스 로그", level = "TRACE") // named argument 를 사용하면 순서를 바꿀 수도 있다. 빌더 패턴과 비슷
}



