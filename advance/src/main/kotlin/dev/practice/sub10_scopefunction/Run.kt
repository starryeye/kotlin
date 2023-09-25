package dev.practice.sub10_scopefunction

/**
 * [3]
 *
 * run
 * - this 로 수신자 객체 참조, 함수의 마지막 코드 줄의 값이 반환 값, 확장 함수로 구현됨
 *
 * 수신자 객체의 프로퍼티를 구성하거나 새로운 결과를 반환하고 싶을 때 사용한다.
 *
 * this 를 생략 가능하다.
 *
 * 프로퍼티 접근시..
 * this 를 사용한다고 해서 직접 참조하는 것처럼 생각될 수 있으나 실제로는 setter 를 사용하는 것이다.
 */

// DB Client Mock
class DatabaseClient {
    var url: String? = null // nullable 가변 변수
    var username: String? = null
    var password: String? = null

    // DB 접속 Mocking
    fun connect(): Boolean {
        println("DB 접속 중 ...")
        Thread.sleep(1000) //코틀린에서는 checked exception 에 대해 처리를 강제하지 않는다.
        println("DB 접속 완료")
        return true
    }
}

fun main() {

    // run 스코프 함수를 사용하지 않음..
    val config = DatabaseClient()
    config.url = "localhost:3306"
    config.username = "root"
    config.password = "1234"
    val connected = config.connect()

    println(connected)

    // -------------------------------------

    // run 스코프 함수를 사용
    // 아래와 같이 프로퍼티를 구성할 때 편하다..
    val connected2 = DatabaseClient().run {
        url = "localhost:3306" // run 스코프 함수에서는 this 가 수신자 객체 참조로 사용된다. (this 는 생략가능..)
        username = "root" // this 라서 뭔가 setter 가 아닌 것 처럼 보이지만 실제로는 setter 를 사용하는 것이다.
        password = "1234"
        connect() // 마지막 줄이 반환 값으로 이용 된다.
    }

    println(connected2)

    // --------------------------------------

    // 아래와 같이 let 스코프 함수를 사용할 수 도 있지만.. it 을 생략할 수 없다.
    // -> let 본연의 목적에 맞게 사용하자.. 아래의 경우 DatabaseClient 가 null 일수 있을 때 null 아니면 사용되는 .. 목적..
    val connected3 = DatabaseClient().let {
        it.url = "localhost:3306"
        it.username = "root"
        it.password = "1234"
        it.connect()
    }
    println(connected3)

    // --------------------------------------


    val databaseClient = DatabaseClient()

    // 아래 코드 보면 살짝 어려울 수 있는데 순서대로 하나씩 보면 쉽다.
    // databaseClient 의 connect() 함수를 수행한다.
    // run 스코프 함수를 수행하는데.. 수행될 로직은 println(this) 이다.
    // this (수신자 객체) 는 databaseClient.connect() 값 (true) 이다.
    // 아래에서는 run 의 반환 값을 사용하지 않았지만 println(this) 의 반환 값이 반환될 것이다. (Unit)
    databaseClient.connect().run { println(this) }


    // --------------------------------------

    // run 과 상당히 유사한 with.. With.kt 를 먼저 보고 아래를 보면 잘 이해됨
    val connected4 = with(DatabaseClient()) {
        url = "localhost:3306"
        username = "root"
        password = "1234"
        connect()
    }
    println(connected4)
}