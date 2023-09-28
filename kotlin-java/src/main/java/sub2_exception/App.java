package sub2_exception;

import java.io.IOException;

public class App {

    /**
     * 코틀린에서 발생한 Checked Exception 을 자바에서 처리해본다.
     *
     * 코틀린에서 발생한 Checked Exception 이 자바로 넘어왔을 때 예외처리가 강제 되지 않는다.
     *
     */

    public static void main(String[] args) {

        KotlinThrow kotlinThrow = new KotlinThrow();
        kotlinThrow.throwCheckedException(); // 코틀린에서 발생한 Checked Exception 이 자바로 넘어왔을 때 예외처리가 강제 되지 않는다.
        // -> try - catch 로 IOException 을 잡을 수 없다. (compile error),

        // 참고.. Java 에서는 throws 키워드를 보고 try-catch 가 강제되도록한다. (트리거)

        //-----------------------------------------------

        // @Throw 어노테이션을 통하여 Java 코드에서 기존 처럼 예외 처리가 강제되도록 해준다.
        try {
            kotlinThrow.throwCheckedException2();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
