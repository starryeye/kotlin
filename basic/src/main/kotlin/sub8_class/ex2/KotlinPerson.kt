package sub8_class.ex2

class KotlinPerson(val name: String, var age: Int) {

    /**
     * 코틀린에서는 클래스 생성 시점에 한번 호출되는 init {} 이라는 블록이 존재한다.
     * 이곳에서 검증 로직을 수행할 수 있다.
     */
    init {
        if (age < 0) {
            throw IllegalArgumentException("age < 0")
        }
    }

    /**
     * 코틀린에서는 또다른 생성자를 만들때는 아래와 같이 constructor 키워드와 함께 만들어 줄 수 있다.
     * 이를 Secondary constructor 라 부른다.
     * secondary constructor 는 최종적으로는 반드시 primary constructor 를 호출하는 구조여야한다.
     *
     * 최초의 클래스 명 옆의 생성자를 primary constructor 라 부르고
     * 필드가 없는 경우를 제외하고 반드시 존재해야한다.
     *
     *
     * 참고
     * 하지만, 코틀린에서는 secondary constructor 를 사용하기보다 primary constructor 에서 기본값을 적용시키는게 권장된다.
     * 기본값을 상황에 따라 쓰기 어려운 경우가 있다면 정적팩토리 메서드를 사용하자.
     */
    constructor(name: String) : this(name, 0) // ": this()" 로 primary constructor 호출

    constructor() : this("unknown") { // ": this()" 로 secondary constructor 호출
        println("secondary constructor") // body 를 만들어 줄 수 도 있다.
    }


}