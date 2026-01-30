package sub8_class.ex1

class KotlinPerson1 constructor(name: String, age: Int) {

    // 필드 선언
    val name: String = name
    var age: Int = age

    /**
     * 생성자는 constructor() 로 class 명 바로 뒤에 위치할 수 있다.
     *      constructor 키워드는 생략이가능하다.
     *      생성자에서 필드선언도 동시에 할 수 있다.
     *
     * 생성자에 타입이 존재하고 해당 값을 필드에 넣어주고 있기 때문에 필드에는 타입을 생략할 수 있다.
     * 필드만 만들면, getter 와 setter 를 자동으로 만들어주고 이는 곧 프로퍼티가 된다.
     * 바디 부분이 없으면 중괄호도 생략 가능하다.
     *
     * 생략 가능한 부분 적용 -> KotlinPerson2
     *
     * 참고
     * 프로퍼티 = 필드 + getter + setter
     */
}