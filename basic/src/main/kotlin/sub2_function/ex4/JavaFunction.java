package sub2_function.ex4;

public class JavaFunction {

    /**
     * Java 의 가변인자.
     */

    public static void main(String[] args) {

        // 호출 방법1
        String[] array = new String[]{"a", "b", "c"};
        printAll(array);

        // 호출 방법2
        printAll("aa", "bb");
    }

    private static void printAll(String... strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
