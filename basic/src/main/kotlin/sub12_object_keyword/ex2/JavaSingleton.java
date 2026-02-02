package sub12_object_keyword.ex2;

public class JavaSingleton {

    /**
     * lazy 한 instance 생성은 아니지만 thread-safe 이다.
     */

    private static final JavaSingleton INSTANCE = new JavaSingleton();

    private JavaSingleton() {}

    public static JavaSingleton getInstance() {
        return INSTANCE;
    }
}
