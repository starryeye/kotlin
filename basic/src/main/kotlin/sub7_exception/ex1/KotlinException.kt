package sub7_exception.ex1

import java.io.BufferedReader
import java.io.FileReader

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

    /**
     * Java 의 try-with-resources 는
     * 코틀린에서는 없다.
     * 대신, use 라는 inline 확장함수를 사용한다.
     */
    fun readFile(path: String) {
        BufferedReader(FileReader(path)).use { br ->
            println(br.readLine())
        }
    }
}