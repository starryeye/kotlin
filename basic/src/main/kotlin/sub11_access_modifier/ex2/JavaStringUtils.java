package sub11_access_modifier.ex2;

public abstract class JavaStringUtils {

    /**
     * Java 에서 유틸성 클래스를 만들때
     * abstract class + 생성자를 private 로 막고 + 메서드는 public static 으로 사용했었다..
     */

    private JavaStringUtils() {}

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
