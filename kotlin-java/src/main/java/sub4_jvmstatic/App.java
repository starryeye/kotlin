package sub4_jvmstatic;

public class App {

    /**
     * 코틀린의 Companion Object (동반 객체, 내부 static 클래스) 의 static 함수 호출하는 방법
     *
     * 코틀린의 Object 객체의 함수 호출하는 방법
     */

    public static void main(String[] args) {

        CompanionObject.Companion.hello();
        // 원래는 위처럼 호출해야하지만, @JvmStatic 어노테이션으로 Java 의 static 내부 클래스의 static 메서드 호출 처럼 호출 가능
        CompanionObject.hello();

//        CompanionObject.Companion.getId();
        // 원래는 위와 같이 접근해야하지만 @JvmField 로 아래와같이 접근가능
        int id = CompanionObject.id;

        String objectName = CompanionObject.OBJECT_NAME; // const val 로 선언한 변수는 바로 접근가능

        //-------------------------------------------------------------

        Singleton.INSTANCE.world();
        // 원래는 위처럼 호출해야하지만, @JvmStatic 어노테이션으로 Java 의 static 메서드 호출하는 것 처럼 호출 가능
        Singleton.world();

//        Singleton.INSTANCE.getId();
        // 원래는 위와 같이 접근해야하지만 @JvmField 로 아래와같이 접근가능
        int id1 = Singleton.id;

        String singletonName = Singleton.SINGLETON_NAME; // const val 로 선언한 변수는 바로 접근가능


    }
}
