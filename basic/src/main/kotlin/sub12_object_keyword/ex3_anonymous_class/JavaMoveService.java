package sub12_object_keyword.ex3_anonymous_class;

public class JavaMoveService {

    public static void main(String[] args) {


        /**
         * Java 에서의 익명 클래스
         */

        move(new JavaMoveable() {
            @Override
            public void move() {
                System.out.println("java anonymous class");
            }

            @Override
            public void fly() {
                System.out.println("java anonymous class");
            }
        });
    }

    private static void move(JavaMoveable moveable) {
        moveable.move();
        moveable.fly();
    }
}
