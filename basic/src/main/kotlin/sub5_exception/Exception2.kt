package sub5_exception

fun main() {

    val str : String? = null

    val result1 : String = str ?: "string"
    println(result1.length)

    // Nothing 일 경우 Elvis 연산자와 함께 사용하면 null 에 대한 안전 코드를 작성하지 않아도 된다. (경고를 발생시키지 않음)
    // -> 위 코드를 보면 알겠지만 무슨 차이인지 모르겠음 추가 공부가 필요함...
    val result2 = str ?: throwException2()
    println(result2.length)


}

fun throwException2() : Nothing {
    throw IllegalStateException("비정상 상태 예외 발생")
}