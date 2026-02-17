package sub28_scope_function.ex1;

import java.util.Optional;

public class JavaMapping {

    public static void main(String[] args) {

        // Kotlin let 대응 예제
        int len = Optional.ofNullable(getString())
                .map(String::length)
                .orElseThrow(() -> new IllegalStateException("str is null"));

        System.out.println("let example result = " + len);


        // Kotlin run 대응 예제
        int computed = Optional.ofNullable(getString())
                .map(str -> str.length() + 10)
                .orElseGet(JavaMapping::defaultValue);

        System.out.println("run example result = " + computed);


        // Kotlin also 대응 예제
        Optional.ofNullable(getString())
                .ifPresent(str -> System.out.println("also example log = " + str));


        // Kotlin apply 대응 예제
        User user = Optional.ofNullable(createUser())
                .map(u -> {
                    u.setName("kim");
                    u.setAge(20);
                    return u;
                })
                .orElseThrow(() -> new IllegalStateException("user is null"));

        System.out.println("apply example result = " + user);


        // Kotlin takeIf 대응 예제
        String filtered = Optional.ofNullable(getString())
                .filter(str -> str.length() > 5)
                .orElseThrow(() -> new IllegalArgumentException("length <= 5"));

        System.out.println("takeIf example result = " + filtered);
    }

    // ------------------------------------
    // helpers
    // ------------------------------------

    private static String getString() {
        return "hello world";
    }

    private static int defaultValue() {
        return 0;
    }

    private static User createUser() {
        return new User();
    }

    // ------------------------------------
    // simple model
    // ------------------------------------

    private static class User {
        private String name;
        private int age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{name='%s', age=%d}".formatted(name, age);
        }
    }
}
