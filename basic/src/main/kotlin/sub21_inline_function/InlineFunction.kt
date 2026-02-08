package sub21_inline_function

/**
 * inline 함수..
 *      함수 호출을 없애고, 함수 본문을 호출 지점에 그대로 복사해 넣는 것
 *
 * 해당 main 함수는 Kotlin 컴파일러에 의해 사실상 아래와 같이 변경?된다..
 *      foo() 함수를 호출하여 콜스택을 만들지 않고 foo() 함수의 코드가 main 에 복사된것으로 된다.
 *
 * fun main() {
 *     println("hi")
 * }
 */
fun main() {
    foo()
}

/**
 * inline 함수로 선언하였다.
 */
inline fun foo() {
    println("hi")
}

