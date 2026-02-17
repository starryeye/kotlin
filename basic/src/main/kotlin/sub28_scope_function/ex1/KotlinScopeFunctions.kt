package sub28_scope_function.ex1

fun main() {

    /**
     * Kotlin 의 scope function 은 Java 에서 뭐랑 매핑된다는게 딱히 없다..
     *
     * let + safe-call operator
     *      null 체크 + 변환 / Optional.map
     * run
     *      임시 변수 + 블록
     * with
     *      임시 변수 + 블록
     * apply
     *      setter 호출 / builder
     * also
     *      중간 로그 / 검증
     * takeIf
     *      if + 조건
     * takeUnless
     *      if + 조건 반대
     */

    // ---------------------------------
    // Kotlin let(람다 결과 반환) -> null-safe 변환, Java의 Optional.map + orElseThrow 대응
    // Java:
    // Optional.ofNullable(getString())
    //     .map(String::length)
    //     .orElseThrow(...)
    // ---------------------------------
    val len = getString()
        ?.let { it.length }
        ?: throw IllegalStateException("str is null")

    println("let example result = $len")


    // ---------------------------------
    // Kotlin run(람다 결과 반환) -> this 기반 계산용, 여러 프로퍼티/함수 접근하여 계산후 결과 반환
    // Java:
    // Optional.ofNullable(getString())
    //     .map(str -> str.length() + 10)
    //     .orElseGet(this::defaultValue)
    // ---------------------------------
    val computed = getString()
        ?.run { length + 10 }
        ?: defaultValue()

    println("run example result = $computed")


    // ---------------------------------
    // Kotlin also(객체 그자체 반환) -> 로깅용
    // Java:
    // Optional.ofNullable(getString())
    //     .ifPresent(str -> log...)
    // ---------------------------------
    getString()
        ?.also { println("also example log = $it") }


    // ---------------------------------
    // Kotlin apply(객체 그자체 반환) -> 객체 초기화, builder
    // Java:
    // Optional.ofNullable(createUser())
    //     .map(u -> { u.setX(); return u; })
    //     .orElseThrow(...)
    // ---------------------------------
    val user = createUser()
        ?.apply {
            name = "kim"
            age = 20
        }
        ?: throw IllegalStateException("user is null")

    println("apply example result = $user")


    // ---------------------------------
    // Kotlin takeIf -> 검증/필터링용, 조건 실패 시 null로 흘려보내기.. 이후 ?:로 종결
    // Java:
    // Optional.ofNullable(getString())
    //     .filter(str -> str.length() > 5)
    //     .orElseThrow(...)
    // ---------------------------------
    val filtered = getString()
        ?.takeIf { it.length > 5 }
        ?: throw IllegalArgumentException("length <= 5")

    println("takeIf example result = $filtered")
}

// ---------------------------------
// helpers
// ---------------------------------

fun getString(): String? =
    "hello world"

fun defaultValue(): Int =
    0

fun createUser(): User? =
    User()

// ---------------------------------
// simple model
// ---------------------------------

class User {
    var name: String = ""
    var age: Int = 0

    override fun toString(): String {
        return "User(name='$name', age=$age)"
    }
}
