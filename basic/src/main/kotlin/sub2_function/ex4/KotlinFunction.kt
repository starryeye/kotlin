package sub2_function.ex4

fun main() {


    // 호출 방법 1
    val array = arrayOf("a", "b", "c")
    printAll(*array) // spread 연산자 사용

    // 호출 방법 2
    printAll("a", "b", "c")

}

fun printAll(vararg strings: String) { // vararg 키워드 사용..
    for (string in strings) {
        println(string)
    }
}