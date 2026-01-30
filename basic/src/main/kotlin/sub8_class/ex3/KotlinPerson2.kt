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
}