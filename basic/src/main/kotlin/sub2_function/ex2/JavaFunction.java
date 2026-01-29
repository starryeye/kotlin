package sub2_function.ex2;

public class JavaFunction {


    private void repeat(String str, int num, boolean useNewLine) {
        for (int i = 0; i < num; i++) {
            if (useNewLine) {
                System.out.println(str);
            } else  {
                System.out.print(str);
            }
        }
    }

    // 파라미터 기본값을 위한 메서드 오버로딩 1
    private void repeat(String str, int num) {
        repeat(str, num, true);
    }

    // 파라미터 기본값을 위한 메서드 오버로딩 2
    private void repeat(String str) {
        repeat(str, 3, true);
    }
}
