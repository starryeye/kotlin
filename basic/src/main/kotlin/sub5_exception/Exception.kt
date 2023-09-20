package sub5_exception

/**
 * 코틀린에서의 예외처리를 알아본다.
 *
 * 자바에서는 Checked Exception 에 대해 try-catch 나 throws 를 강제한다. (by 컴파일러)
 *
 * 코틀린에서는 Checked Exception 에 대해 자바와 같은 예외처리를 강제하지 않는다.
 * -> ex) Thread.sleep(1000) 을 예외처리 하지 않아도 된다.
 * -> 굳이 쓰고 싶다면 쓰면된다.
 */

fun main() {

    Thread.sleep(1000)

    throwException()

    // throwException 함수에서 리턴 타입이 Nothing 이라서, 컴파일러가 경고를 보낼 수 있다. unreachable code
    println("unreachable")
}

// 리턴 타입이 Nothing 인데.. 이는 리턴이라는 행위 자체를 수행하지 않는다는 것이다. (즉, 정상 리턴이 이루어지지 않음)
// Unit 과 비교하자면.. Unit 은 리턴 값이 없다는 것이지.. 리턴 행위는 한다..
fun throwException() : Nothing {
    throw IllegalStateException("비정상 상태 예외 발생")
}