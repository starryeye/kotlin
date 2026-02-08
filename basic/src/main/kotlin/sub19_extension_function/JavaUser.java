package sub19_extension_function;

public class JavaUser {

    private final String name;
    private int age;

    public JavaUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int nextYearAge() {
        System.out.println("Java 맴버 함수");
        return age + 1;
    }
}
