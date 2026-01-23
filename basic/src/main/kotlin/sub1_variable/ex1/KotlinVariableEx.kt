package sub1_variable.ex1

fun main() {

    var number1 = 10L // 가변
    val number2 = 10L // 불변

    var number3 = 1_000L // primitive type 은 없다 생각하자.
    var number4 = 1_000L

    val person = Person("AAA") // 인스턴스 생성 시, new 키워드 없음
}

private data class Person(
    val name: String
)