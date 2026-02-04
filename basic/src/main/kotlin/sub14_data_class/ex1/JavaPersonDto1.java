package sub14_data_class.ex1;

import java.util.Objects;

public class JavaPersonDto1 {

    private final String name;
    private final int age;

    private JavaPersonDto1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static JavaPersonDto1 createBaby(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is null or blank");
        }
        return new JavaPersonDto1(name, 1);
    }

    public static JavaPersonDto1 create(String name, int age) {
        if (name == null  || name.isBlank()) {
            throw new IllegalArgumentException("name is null or blank");
        }

        if (age < 1) {
            throw new IllegalArgumentException("age is negative");
        }
        return new JavaPersonDto1(name, age);
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JavaPersonDto1 that = (JavaPersonDto1) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "JavaPersonDto1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
