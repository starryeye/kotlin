package sub4_null_safety

/**
 * 코틀린에서 NPE 발생하는 경우
 */

fun main() {

    //직접 NPE
    throw NullPointerException() // NPE 발생

    //-------------------------------------------------

    // null 이 될 수 있는데 개발자가 null 이 절대로 될 수 없다고 "단언연산자" 를 사용하여 null 을 참조하게 되는 경우
    val str: String? = null
    str!!.length // NPE 발생

    //-------------------------------------------------

    //Java 코드를 사용할 경우 코틀린 컴파일러가 체크해주지 못한다..
    JavaNullSafety.getNullStr().length // NPE 발생
}
