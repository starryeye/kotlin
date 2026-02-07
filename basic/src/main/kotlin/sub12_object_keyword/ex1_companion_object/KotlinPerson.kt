package sub12_object_keyword.ex1_companion_object

class KotlinPerson(
    private var name: String, // Java 코드에 getter, setter 가 없으므로 private
    private var age: Int, // Java 코드에 getter, setter 가 없으므로 private
) {

    companion object Factory {
        private const val MIN_AGE = 1
        fun baby(name: String) = KotlinPerson(name, MIN_AGE)
    }

    /**
     * 코틀린에서는 Java 의 static 키워드가 없다.
     * Java 의 static 변수 / static 메서드는
     *      Kotlin 에서는 companion object 블록 내에 있는 변수 / 함수가 대체한다.
     *
     * companion object
     *      -> class 라는 설계도와 유일하게 "동행하는 object" 라고 기억해보자
     *
     * companion object 는 하나의 객체이므로 이름을 붙일 수 있다. (예시에서는 Factory 라는 이름)
     *      생략할 수 도 있다.
     *
     * companion object 는 interface 구현도 할 수 있다.
     * companion object 가 static 대체다 보니 유틸성 함수를 companion object 내에 위치시키고 싶을 수 있으나..
     *      class 외부에 파일 최상단에 위치시키도록 하자. (sub11_access_modifier.ex2 참고)
     *
     * companion object 를 Java 코드에서 접근하는 방법..
     * 1. companion object 의 이름이 없는 경우..
     *      KotlinPerson.companion.baby("baby1");
     * 2. companion object 의 이름이 있는 경우..
     *      이름이 Factory 라면.. KotlinPerson.Factory.baby("baby2")
     * 3. fun baby(name: String) 에 @JvmStatic 을 적용시킨 경우
     *      KotlinPerson.baby("baby3")
     */
}