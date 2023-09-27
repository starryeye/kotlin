package dev.practice.sub13_functionalprogramming

/**
 * [7]
 *
 * 람다 레퍼런스
 *
 * 함수를 표현하는 또다른 방법..
 *
 * {클래스 지시자}::{함수명}
 * - 클래스에 속한게 아니거나(탑레벨) 로컬에 정의된 함수라면 클래스 지시자는 생략 가능하다.
 */

fun main() {

    val lambdaReference = ::getPrintKotlin // TODO 이거 타입 보면 어지럽다.. 해석해봐..
    lambdaReference()()

    val lambdaReference2 =  ::printKotlin // TODO 이거 타입 보면 어지럽다.. 해석해봐..
    lambdaReference2()

    // ---------------------------------------------------------------------------

    // 람다 레퍼런스 사용 안함 (람다 식과 passing trailing lambda 문법 사용)
    // TODO 코틀린에서 java stream 대신에 밑에 처럼쓰는데.. 뭔가 주의사항이 있었던거 같음..
    // -> Collection.kt
    val listOf = listOf("1", "2", "3")
    listOf.map {
        it.toInt()
    }.forEach{
        println(it)
    }

    // 람다 레퍼런스 사용
    // String 에 있는 toInt 함수 사용 (확장 함수긴함)
    // println 은 어느 클래스에 속한 함수가 아니라 탑레벨에 있는 함수이다. 따라서 클래스 지시자는 없어도 된다.
    listOf.map(String::toInt).forEach(::println)
}


fun getPrintKotlin(): () -> Unit = { println("Kotlin") } // 함수를 리턴하는 고차 함수
fun printKotlin() = { println("Kotlin") } // 위의 getPrintKotlin 과 전혀 다른 함수이다. 반드시 구분할 수 있어야한다. (사실 중괄호 생략하면 좀 헷갈리긴함)