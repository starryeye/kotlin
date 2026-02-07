package sub8_class.ex1_property_and_primary_constructor;

public class JavaPerson {

    // 필드 선언
    private final String name; // 이름은 불변
    private int age;

    /**
     * 필드가 선언되고 getter/setter 가 존재하여 두 필드는 프로퍼티로 부른다.
     */

    public JavaPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
