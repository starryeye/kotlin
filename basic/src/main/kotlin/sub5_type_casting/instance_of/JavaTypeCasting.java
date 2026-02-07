package sub5_type_casting.instance_of;

public class JavaTypeCasting {

    public static void printAgeIfPerson11(Object o) {

        if (o instanceof Person) {
            Person p = (Person) o;
            System.out.println(p.getAge());
        }
    }

    public static void printAgeIfPerson12(Object o) {

        if (o instanceof Person p) {
            System.out.println(p.getAge());
        }
    }

    public static void printAgeIfPerson2(Object o) {

        if (!(o instanceof Person)) {
            // do something..
        }
    }


    private static class Person {
        private int age;

        public int getAge() {
            return age;
        }
    }
}
