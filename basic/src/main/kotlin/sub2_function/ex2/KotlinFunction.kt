package sub2_function.ex2

/**
 * 코틀린에서는 파라미터 기본값을 위해 여러개의 오버로딩 메서드를 작성할 필요가 없다.
 * 물론, 코틀린에서도 오버로딩 기능은 있다.
 */
fun repeat(str: String, num: Int = 3, useNewLine: Boolean = true) {
    for (i in 0 until num) {
        if (useNewLine) {
            println(str)
        } else {
            print(str)
        }
    }
}