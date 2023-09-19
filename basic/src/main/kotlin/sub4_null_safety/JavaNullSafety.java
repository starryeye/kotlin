package sub4_null_safety;

import java.util.Optional;

/**
 * Java 에서 null 을 다루는 방법...
 *  Optional..
 *
 *  아래 코드는 자바코드라서 컴파일 되지 않는다. (읽고 참고용으로만 사용)
 */

public class JavaNullSafety {

    public static String getNullStr() {
        return null;
    }

    public static int getLengthIfNotNull(String str) {
        if(str == null || str.isEmpty()) {
            return 0;
        }
        return str.length();
    }

    public static void main(String[] args) {

        String nullable = getNullStr();
        String optionalString = Optional.ofNullable(nullable).orElse("null 이다.");

        System.out.println(optionalString.length()); //Optional 로 wrapping 해놓았기 때문에 NPE 발행하지 않음

        System.out.println(getLengthIfNotNull(null));
    }
}
