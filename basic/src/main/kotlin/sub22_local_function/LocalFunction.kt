package sub22_local_function

fun main() {

    /**
     * local 함수
     *      function 내부에 function 선언한 함수이다.
     *
     * 선언된 함수 내부에서만 사용 가능하고, 바깥에서는 접근할 수 없다
     * 작은 로직을 의미 단위로 분리할 때 사용
     * 로컬함수 내에서 로컬함수 외부의 변수는 변수캡쳐링된다.
     */
    fun printHello() {
        println("hello")
    }

    printHello() // local 함수를 사용
}