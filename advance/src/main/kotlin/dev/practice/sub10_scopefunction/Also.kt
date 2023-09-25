package dev.practice.sub10_scopefunction

/**
 * [6]
 *
 * also
 * - it 으로 수신자 객체 참조, 컨텍스트 객체가 반환 값, 확장 함수로 구현됨
 *
 * 부가 작업을 수행하고 전달받은 수신자 객체를 그대로 결과로 반환하고 싶을 때 사용 된다.
 *
 */

class User(private val name: String, private val password: String) {

    fun validation() {
        if(name.isNotEmpty() && password.isNotEmpty()) {
            println("name and password valid")
        } else {
            println("name and password invalid")
        }
    }

    fun printName() = println(name)
}

fun main() {
    // also 스코프 함수를 사용 하지 않음
    val user: User = User(name = "Alice", password = "1234")
    user.validation()
    user.printName()

    // ------------------------------------------

    // also 스코프 함수를 사용함
    User(name = "Bob", password = "1234").also { // 확장 함수로 구현됨을 알 수 있다.
        it.validation()
        it.printName()
    }
}