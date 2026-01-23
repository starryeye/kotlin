package sub1_variable.ex1;

public class JavaVariableEx {

    public static void main(String[] args) {

        long number1 = 10L;
        final long number2 = 10L;

        Long number3 = 1_000L;
        long number4 = 1_000L;

        Person person = new Person("AAA");
    }

    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }
    }
}
