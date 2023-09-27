package dev.practice.sub13_functionalprogramming

/**
 * [4]
 *
 * 코틀린에서는
 * 익명 함수를 람다 표현식으로 대체 할 수 있다.
 *
 * <참고>
 * Java 에서는 함수형 인터페이스의 구현체를 람다 표현식으로 구현할 수 있었다.
 */

// 익명 함수
fun getFunc(): () -> Unit {
    return fun() {
        println("익명 함수")
    }
}

// getFunc 와 getFunc2 는 동일하다.
// 익명함수를 람다 표현식으로 대체 하였다.
fun getFunc2(): () -> Unit {

    return { println("람다 표현식") }
}

// 더 간결하게 나타냄
fun getFunc3(): () -> Unit = { println("람다 표현식") }

fun main() {

    getFunc()()
    getFunc2()()
    getFunc3()()

    /**
     * sumInt2, 3 은 꼭 비교해서 볼 것..
     */

    sumInt()(1, 2)
    sumInt2()(1, 2)
    sumInt3()(1, 2)
    sumInt4()(1, 2)

}

//익명 함수
fun sumInt(): (Int, Int) -> Int {
    return fun(a: Int, b: Int) : Int { // 반환 타입 꼭 해주자....!!!
        return a + b
    }
}

//익명 함수
fun sumInt2(): (Int, Int) -> Int = fun(a: Int, b: Int): Int { return a + b }

//람다 식
fun sumInt3(): (Int, Int) -> Int = { a: Int, b:Int -> a + b }

//람다 식 (최대 생략 버전)
fun sumInt4() = { a: Int, b: Int -> a + b }