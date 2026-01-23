package sub4_null_safety.ex2

class KotlinNullSafety {


    /**
     * 기본적으로 Java 에서 안전연산자만 붙이면 된다.
     *
     * 참고
     * 아래의 경우 if 로 null 체크 대신 ?: (엘비스 연산자) 로 대체 해도 된다.
     * 특히, 두번째의 경우엔 null 이면 null 을 리턴하므로 ?: 연산자도 필요없다.
     *      KotlinNullSafety2 참고
     */

    // null 이면 예외 발생
    fun startsWithA1(str: String?): Boolean {
        if (str == null) {
            throw IllegalArgumentException("null..")
        }
        return str.startsWith("A") // null check 가 이루어졌으므로 안전연산자 필요 없다.
    }

    // null 이면 null 반환
    fun startsWithA2(str: String?): Boolean? {
        if (str == null) {
            return null
        }
        return str.startsWith("A") // null check 가 이루어졌으므로 안전연산자 필요 없다.
    }

    // null 이면 다른 값(false) 반환
    fun startsWithA3(str: String?): Boolean {
        if (str == null) {
            return false
        }
        return str.startsWith("A") // null check 가 이루어졌으므로 안전연산자 필요 없다.
    }


    // early return
    fun plus10(number: Long?): Long {

        number ?: return 0

        return number + 10 // null check 가 이루어졌으므로 안전연산자 필요 없다.
    }
}