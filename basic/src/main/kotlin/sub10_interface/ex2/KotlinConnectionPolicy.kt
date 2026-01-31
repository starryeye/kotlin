package sub10_interface.ex2

interface KotlinConnectionPolicy {

    val host: String // getter 계약
    val port: Int // getter 계약
    var timeoutMs: Long // getter, setter 계약

    val endpoint: String
        get() = "$host:$port" // default implementation, computed property

    /**
     * 인터페이스의 프로퍼티는 "값(필드)"가 아니라 "계약(contract)"이다.
     *      구현 클래스가 반드시 제공해야 하는 getter/setter 규약이 된다.
     * 인터페이스에는 상태(저장 공간)가 없어서 backing field(field)가 없다.
     *      그래서 인터페이스 프로퍼티에는 초기값을 둘 수 없다.
     * 대신, 인터페이스는 getter/setter의 "기본 구현(default implementation)"은 제공할 수 있다.
     *      이는 계산된(computed) 프로퍼티 형태로 제공된다.
     */
}