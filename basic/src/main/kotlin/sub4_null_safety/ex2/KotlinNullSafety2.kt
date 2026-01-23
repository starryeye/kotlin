package sub4_null_safety.ex2

class KotlinNullSafety2 {

    // null 이면 예외 발생
    fun startsWithA1(str: String?): Boolean =
        str?.startsWith("A") ?: throw IllegalArgumentException("null..")

    // null 이면 null 반환
    fun startsWithA2(str: String?): Boolean? =
        str?.startsWith("A")

    // null 이면 다른 값(false) 반환
    fun startsWithA3(str: String?): Boolean =
        str?.startsWith("A") ?: false
}