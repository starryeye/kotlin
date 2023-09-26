package dev.practice.sub12_runcatching

/**
 * 코틀린은 try-catch 를 통한 예외 처리 외에도..
 * 함수형 스타일의 Result 패턴을 구현한 runCatching 을 제공한다.
 *
 * Result 패턴은..
 * 함수가 성공하면 캡슐화된 결과를 반환하거나 예외가 발생하면 지정한 작업을 수행하는 패턴이다.
 * -> Java 의 Optional 과 비슷한듯...
 *
 * runCatching 은 코드 블럭 수행하고
 * 결과는 Result 객체를 반환한다.
 *
 * Result 객체는.. 수신자 객체(runCatching 코드 블럭 결과) 가 성공이냐 실패냐에 따라 다른 처리를 할 수 있도록 해준다.
 *
 * - getOrNull : 성공이면 수신자 객체 그대로 반환, 실패면 null 반환 (파라미터가 없다.)
 * - exceptionOrNull : 성공이면 null 반환, 실패면 Throwable 반환 (발생은 아님)
 * - getOrDefault : 성공이면 수신자 객체 그대로 반환, 실패면 전달된 파라미터 값 반환 (파라미터가 있다.)
 * - getOrElse : 성공이면 수신자 객체 그대로 반환, 실패면 getOrElse 코드블럭 수행후 결과값 반환
 * - getOrThrow : 성공이면 수신자 객체 그대로 반환, 예외 발생하면(실패)하면 해당 예외 그대로 발생시킴 (파라미터가 없다.)
 * - map : 성공이면 map 코드 블럭을 수행한다.
 * - mapCatching : 아래 예시를 보면 이해가 될 것이다....
 * - recover : map 과 반대로 실패면 recover 코드 블럭을 수행한다.
 * - recoverCatching : mapCatching 과 반대이다.
 * 등 을 제공한다.
 *
 * Result.kt 의 코드들을 보면 이해에 도움될 것이다...
 */

fun main() {

    // runCatching 사용하지 않음
    val result = try {

        throw Exception("1 예외 발생")

    } catch (e: Exception) {

        println(e.message)
        "1 기본 값"
    }
    println(result)

    // ----------------------------------------

    //runCatching 사용
    val result2 = runCatching {

        throw Exception("2 예외 발생")

    }.getOrElse {

        println(it.message)
        "2 기본 값"
    }
    println(result2)

    // -----------------------------------------

    // getOrNull
    val result3 = runCatching {
        "3 success"
    }.getOrNull()
    println(result3)

    // ----------------------------------------

    // exceptionOrNull
    val result4 = runCatching {
        throw Exception("4 예외 발생")
    }.exceptionOrNull()

    // ----------------------------------------

    // map + getOrThrow
    val result5 = runCatching {
        "5 runCatching success"
    }.map {
        it + " map" // runCatching 성공하면, 여기 수행
    }.getOrThrow() // 예외가 발생하면(실패하면).. 예외를 발생시킨다.


    // map + getOrDefault
//    val result6 = runCatching {
//        "6 runCatching success"
//    }.map {
//        throw Exception("6 예외 발생")
//    }.getOrDefault("6 기본 값")
//    println(result6)

    /**
     * 위 코드는 헷갈릴 수 있는데..
     * map 이 runCatching 이었다면.. 상상한 것 처럼 6 기본 값이 반환될 것이지만..
     * map 이므로 그냥 예외가 발생해버린다..!!
     *
     * 이럴 때, mapCatching 을 사용한다.
     */


    // ----------------------------------------

    // mapCatching
    val result7 = runCatching {
        "7 runCatching success"
    }.mapCatching {
        throw Exception("7 예외 발생")
    }.getOrDefault("7 기본 값")

    println(result7)
}