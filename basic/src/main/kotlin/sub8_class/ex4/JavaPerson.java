package sub8_class.ex4;

public class JavaPerson {

    private final String name; // 이름은 불변
    private int age;

    public JavaPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name.toUpperCase(); // getName 할때 무조건 대문자로 반환하도록 한다.
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
