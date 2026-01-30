package sub8_class.ex2;

public class JavaPerson {

    private final String name; // 이름은 불변
    private int age;

    public JavaPerson(String name, int age) {

        if (age < 0) { // Java 에서는 생성자에서 맴버 변수 검증을 할 수 있다.
            throw new IllegalArgumentException("age < 0");
        }
        this.name = name;
        this.age = age;
    }

    public JavaPerson(String name) { // Java 에서는 또다른 생성자를 이렇게 만들 수 있었다.
        this(name, 0);
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
