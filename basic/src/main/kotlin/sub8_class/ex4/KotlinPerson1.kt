package sub8_class.ex4

class KotlinPerson1(name: String, var age: Int) {

    /**
     * name 프로퍼티의 getter 를 커스텀 해주기 위해서 (custom getter)
     * primary constructor 에서 val 을 붙여주지 않고
     * 바디 부분에서 val name 으로 선언함.
     *      primary constructor 괄호내 생성자 파라미터 name 의 값을 name 프로퍼티에 초기화 해주도록 하였다.
     *
     * 코틀린에서는 맴버 변수는 필드가 아닌 !프로퍼티!로 "name" 을
     * class 바디 부분에서 호출하면 JVM 바이트코드 상으로 this.getName() 이 호출된 것이다. (name -> this.name -> this.getName())
     * 따라서.. custom getter 바디부분에서 ..
     *      get() = name.uppercase() 로 호출해버리면..
     *      get() = this.getName().uppercase() 가 되어 무한루프에 빠진다.
     * 코틀린에서는 이를 극복하기 위해 실제 값에 접근할 수 있는 field 라는 키워드를 제공한다.
     *
     * field 라는 키워드를 backing field 라 부른다.
     *
     */
    val name: String = name
        get() = field.uppercase()
}