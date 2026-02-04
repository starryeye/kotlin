package sub14_data_class.ex1;

public record JavaPersonDto2(
        String name,
        int age
) {

    public JavaPersonDto2 {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is null or blank");
        }
        if (age < 1) {
            throw new IllegalArgumentException("age is negative");
        }
    }

    public static JavaPersonDto2 createBaby(String name) {
        return new JavaPersonDto2(name, 1);
    }

    public static JavaPersonDto2 create(String name, int age) {
        return new JavaPersonDto2(name, age);
    }
}
