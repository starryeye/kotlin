package sub5_extensionfunction;

public class App {

    /**
     * 코틀린에서 작성된 확장함수를 자바에서 사용하려면?
     */

    public static void main(String[] args) {

        // 확장 함수가 존재하는 코틀린 파일이름에 Kt 를 더한 클래스가 있다고 생각하고 ... 해줘야한다. 즉, static 메서드로 이용해야한다.
        // 자바에서는 코틀린에서 처럼 확장함수를 이용할 수 없다.
        char first = ExtenstionKt.first("ABCD");
        System.out.println(first);

        String s = ExtenstionKt.addFirst("BC", 'A');
        System.out.println(s);
    }
}
