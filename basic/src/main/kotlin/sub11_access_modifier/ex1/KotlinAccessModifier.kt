package sub11_access_modifier.ex1

class KotlinAccessModifier {

    /**
     * 코틀린 클래스에서의 접근제어
     *
     * public
     *      모든 곳에서 접근가능 (기본 값)
     * protected
     *      선언된 해당 클래스내에서, 하위 클래스에서만 접근가능
     * internal
     *      선언된 해당 클래스내에서, 같은 모듈(하나의 컴파일 프로젝트 모듈을 의미.. 멀티 모듈에서의 하나의 모듈임)
     * private
     *      선언된 해당 클래스내에서만 접근가능
     */

    /**
     * 코틀린 파일에서의 접근제어
     *
     * public
     *      모든 곳에서 접근가능 (기본 값)
     * protected
     *      사용 불가능
     * internal
     *      선언된 해당 파일내에서, 같은 모듈(하나의 컴파일 프로젝트 모듈을 의미.. 멀티 모듈에서의 하나의 모듈임)
     * private
     *      선언된 해당 파일내에서만 접근가능
     */

    /**
     * 주의사항.
     *      1. 코틀린에서 primary constructor 에 접근제어자를 붙이려면
     *              constructor 키워드는 생략 불가하다.
     *                  ex. class Apple internal constructor()
     *      2. internal 은 바이트코드상 public 이다.
     *              따라서, Java 코드로 Kotlin 모듈을 접근하면 internal 일지라도 접근이 가능하다.
     *      3. Java 코드로 같은 패키지에 존재하는 Kotlin protected 에 접근이 가능하다.
     *              왜냐하면, Kotlin 에서는 같은 패키지라도 protected 면 접근이 불가하지만, Java 에서의 protected 는 같은 패키지면 접근 가능하므로
     *
     */
}