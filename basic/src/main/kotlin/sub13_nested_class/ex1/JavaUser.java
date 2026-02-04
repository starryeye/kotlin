package sub13_nested_class.ex1;

public class JavaUser {

    /**
     * Java 에서 클래스 안에 클래스를 만들 때, 권장되는 방식인..
     * static 을 사용한 버전.
     *
     * 대표적 예시인 builder 패턴
     */

    private final String name;
    private final int age;

    private JavaUser(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static class Builder {
        /**
         * static 중첩 클래스 내부에서는 외부 클래스를 직접 참조하지 못한다.
         */
        private String name;
        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public JavaUser build() {
            return new JavaUser(this); // 여기서의 this 는 Builder 인스턴스를 뜻함.
        }
    }
}
