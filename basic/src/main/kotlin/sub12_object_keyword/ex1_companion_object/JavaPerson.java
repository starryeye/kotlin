package sub12_object_keyword.ex1_companion_object;

public class JavaPerson {

    /**
     * Java 의 static
     *      클래스가 인스턴스화 될 때 정적으로 인스턴스끼리 값을 공유한다.. (메서드영역 = 클래스영역 = static 영역)
     */

    private static final int MIN_AGE = 1;

    private String name;
    private int age;

    private JavaPerson(String name, int age) { // 생성자는 private
        this.name = name;
        this.age = age;
    }

    // static factory method
    public static JavaPerson baby(String name) {
        return new JavaPerson(name, MIN_AGE);
    }
}
