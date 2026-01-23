package sub4_null_safety.ex2;

public class JavaNullSafety {


    /**
     * Java 에서 NPE 를 회피하기 위해..
     */

    // null 이면 특정 예외 발생시키기
    public boolean startsWithA1(String str) {
        if (str == null) {
            throw new IllegalArgumentException("null..");
        }
        return str.startsWith("A");
    }

    // null 이면 null 을 리턴하기
    public Boolean startsWithA2(String str) {
        if (str == null) {
            return null;
        }
        return str.startsWith("A");
    }

    // null 이면 다른 값으로 리턴하기
    public boolean startsWithA3(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("A");
    }

    // early return
    public Long plus10(Long number) {

        if (number == null) {
            return 0L;
        }

        return number + 10;
    }
}
