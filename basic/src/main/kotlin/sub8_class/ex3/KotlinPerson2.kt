package sub8_class.ex3

class KotlinPerson2(val name: String, var age: Int) {

    /**
     * custom getter
     *
     * 코틀린에서는 프로퍼티를 선언(getter setter 를 자동으로 만들어줌)하면..
     *      getter 함수 호출할때는 .프로퍼티명으로 호출할 수 있다.
     *      따라서, isAdult 프로퍼티를 선언하면, 마치 isAdult 라는 함수가 존재하는 것 처럼 되고..
     *      getter 함수 로직을 커스텀 해주면 isAdult 라는 함수를 의도대로 만들 수 있다.
     *      이때, getter 함수 로직을 커스텀 하는 것을 custom getter 라 한다.
     *
     * 참고
     * isAdult 는 객체의 속성을 확인하는 것이라서 custom getter 를 쓰는게 맞고
     * 만약 객체의 속성과 관련된게 아니라면, 일반적인 함수로 표현하도록 하자.
     */
    val isAdult: Boolean
        get() = this.age >= 20


    /**
     * 코틀린에서 프로퍼티는 반드시 초기화가 필요하다. (생성자에서 초기화를 하던, 기본값을 주던..)
     *
     * 그러나.. 위의 예제에서는 초기화가 안되었다.
     * 이유 : 코틀린에서의 프로퍼티는 반드시 “초기값” 또는 “custom getter” 중 하나를 가져야 한다.
     *
     * 코틀린의 프로퍼티
     * 일반 프로퍼티 (Stored Property, Property with backing field)
     *      내부적으로 인스턴스에 값을 실제로 저장하고 getter 는 저장된 값을 반환
     * 계산된 프로퍼티 (Computed Property)
     *      내부적으로 인스턴스에 값을 저장하지 않고, 접근 시마다 계산된다. custom getter 만 존재 함.
     *      따라서 계산된 프로퍼티의 get() 함수 바디에서는 backing field 를 사용할 수 없음 (값을 저장하지 않으므로)
     *      Java 관점에서는 필드 없이 getXXX 메서드만 있는 형태
     *
     */
}