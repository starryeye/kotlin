package sub8_class.ex1_property_and_primary_constructor

class KotlinPerson1 constructor(name: String, age: Int) {

    // 프로퍼티 선언, 생성자 파라미터 값으로 초기화하고 있다.
    val name: String = name
    var age: Int = age

    /**
     * 생성자는 constructor() 로 class 명 바로 뒤에 위치할 수 있다.
     *      constructor 키워드는 생략이가능하다.
     *      생성자 괄호 내의 생성자 파라미터에서 프로퍼티선언도 동시에 할 수 있다.
     *          -> KotlinPerson2 참고
     *
     * 생성자 파라미터에 타입이 존재하고 해당 값을 프로퍼티에 넣어주고 있기 때문에 프로퍼티 선언에는 타입을 생략할 수 있다. (타입 추론)
     * 왜 프로퍼티라 부르냐 하면, 코틀린에서 자동으로 getter, setter 를 만들어주기 때문이다.
     * 바디 부분이 없으면 중괄호도 생략 가능하다.
     *
     * 생략 가능한 부분 적용 -> KotlinPerson2
     *
     * 참고
     * Java 에서는 ..
     * 프로퍼티 = 필드 + getter + setter 이다.
     */
}