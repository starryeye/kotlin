package sub8_class.ex3_property_and_custom_getter_and_back_field;

public class JavaPerson {

    private final String name; // 이름은 불변
    private int age;

    public JavaPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean isAdult() { // Java 에서 어떤 값에 대해 확인하는 기능을 넣을 때..
        return this.age >= 20;
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
