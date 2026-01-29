package sub2_function.ex3

fun main() {


    /**
     * 코틀린에서는 named argument 기능이 있어서 동일 타입의 연속적인 파라미터가 있으면..
     * 순서를 헷갈릴 가능성이 줄어듬
     *
     * 마치, builder 패턴을 사용하는 느낌과 비
     *
     * 참고.
     * Java 의 메서드를 코틀린에서 호출할때는 named argument 기능을 사용할 수 없다.
     *      Java 코드가 바이트코드로 변환되었을 때 파라미터 이름은 보존되지 않는다.
     */

    printNameAndGender(name = "AAA", gender = "M")
}

fun printNameAndGender(name: String, gender: String) {
    println(name)
    println(gender)
}