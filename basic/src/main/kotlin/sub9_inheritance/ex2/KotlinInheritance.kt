package sub9_inheritance.ex2

fun main() {
    val derived = Derived(300)
}

open class Base(
    open val number: Int = 100,
) {
    init {
        println("[Base] The number is $number")
    }
}

class Derived(
    override val number: Int,
) : Base(number) {
    init {
        println("[Derived] The number is $number")
    }
}

/**
 * Kotlin 에서 클래스간 상속을 위해서는.. 부모클래스에 open 키워드를 붙여줘야한다.
 *
 * 1. Derived(300) 생성 시작
 *
 * 2. 먼저 Base 쪽 초기화가 먼저 수행되어야 하므로 Base(number) 호출로 들어감
 *
 * 3. Base의 init {} 실행
 *
 * 4. 여기서 number는 open 이라 동적 디스패치가 걸림
 *      Base.number가 아니라 Derived.number(override) 를 읽으려 함
 *      그런데 Derived의 number 프로퍼티는 아직 초기화 전
 *      (JVM 레벨에서 backing field 가 아직 “300 으로 세팅되기 전”)
 *      primitive type int의 기본값은 0
 *      그래서 [Base] The number is 0 출력
 *
 * 5. 그 다음에 Derived 의 프로퍼티 초기화가 진행되어 number=300 이 세팅됨
 *      [Derived] The number is 300 출력
 */