package dev.practice.sub5_sealedclass

/**
 * sealed class 에 대해 알아보겠다.
 *
 * 실드 클래스란..
 * 상위 클래스, 인터페이스에서 하위 클래스 정의를 제한하는 방법이다.
 * -> 기본적으로 abstract 와 비슷하지만.. 컴파일러가 컴파일 시점에 하위 클래스의 존재를 알게되는 듯..
 */

sealed class Developer {
    abstract val name: String
    abstract fun code(language: String)
}

data class BackendDeveloper(override val name: String) : Developer() {
    override fun code(language: String) {
        println("백엔드 개발자, 언어 : $language")
    }
}

data class FrontendDeveloper(override val name: String) : Developer() {
    override fun code(language: String) {
        println("프론트엔드 개발자, 언어 : $language")
    }
}

object DeveloperPool {
    val pool = mutableMapOf<String, Developer>()

    /**
     * Developer 가..
     * abstract class 일 경우..
     * 아래 when 식에서 else 코드를 주석 처리하면..
     * 컴파일 에러가 발생한다.
     * 왜냐하면.. Developer 추상 클래스의 상속이 2가지만 있는지를 모르기때문에 else 를 생략할 수 없기 때문이다.
     *
     * 이때..
     * Developer 가..
     * sealed class 로 하면..
     * 컴파일 시점에 컴파일러가 실드 클래스의 하위 클래스를.. 제한 조건에 따라 정의 해야하므로
     * 하위 클래스를 인지해야한다.
     * 따라서, 상속이 2개만 있다고 판단을 할 수 있으므로 else 를 생략할 수 있다.
     * 물론, 3개가 있지만 2개만 is 로 분기 처리 해놓으면 컴파일 에러 잡아주는건 당연하다.
     *
     * 결론, 런타임 오류를 컴파일 오류로 바꾸어서 좀더 안전한 개발 생활 가능
     *
     */
    fun add(developer: Developer) = when (developer) {
        is BackendDeveloper -> pool[developer.name] = developer
        is FrontendDeveloper -> pool[developer.name] = developer
//        else -> {
//            println("지원하지 않는 개발자이다.")
//        }
    }

    fun get(name: String) = pool[name]
}

fun main() {
    val backendDeveloper = BackendDeveloper(name = "aaa")
    DeveloperPool.add(backendDeveloper)

    val frontendDeveloper = FrontendDeveloper(name = "bbb")
    DeveloperPool.add(frontendDeveloper)

    println(DeveloperPool.get("aaa"))
    println(DeveloperPool.get("bbb"))
}