package dev.practice.sub10_scopefunction

/**
 * [5]
 *
 * apply
 * - this 로 수신자 객체 참조, 컨텍스트 객체가 반환 값, 확장 함수로 구현됨
 *
 * 수신자 객체의 프로퍼티를 구성하고 수신 객체를 그대로 결과로 반환하고 싶을 경우 사용된다.
 */

fun main() {

    // apply 스코프 함수를 사용
    // 앞서 공부한 let, run, with 는 스코프 함수의 결과가 반환 되는데...
    // apply 는 수신자 객체가 그대로 반환된다.
    val client: DatabaseClient = DatabaseClient().apply { // 확장 함수로 구현 됨을 알수 있다.
        url = "localhost:3306" // 수신자 객체 참조로 this 가 사용된다. (생략 가능)
        username = "root"
        password = "1234"
    }

    val connected = client.connect()
    println(connected)

    // --------------------------------------------

    // 스코프 함수 체이닝 응용
    DatabaseClient().apply {
        url = "localhost:3306"
        username = "root"
        password = "1234"
        connect()
    }.connect().run { println(this) }

    // 위 코드 해석..
    // DatabaseClient() 생성자 수행
    // 생성자의 결과 값(인스턴스) 이 수신자 객체가 되는 apply 스코프 함수 수행 (수행 로직은 코드블럭 내부)
    // apply 스코프 함수 결과 값(수신자 객체 그대로 반환) 으로 connect() 함수 수행
    // connect() 함수 의 결과 값(true) 이 수신자 객체가 되는 run 스코프 함수 수행 (수행 로직은 코드블럭 내부)
    // 위에서는 run 의 반환 값을 사용하지 않았지만 println(this) 의 반환 값이 반환될 것이다. (Unit)
}