package sub7_exception.ex1

fun main() {

}

class KotlinException {

    fun parseIntOrThrow(s: String): Int {
        try {
            return s.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("string s is invalid, s = $s")
        }
    }

    fun parseIntOrNull(s: String): Int? {
        return try { // 코틀린에서는 try-catch 가 표현식이다.
            s.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}