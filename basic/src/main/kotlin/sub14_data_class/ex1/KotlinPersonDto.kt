package sub14_data_class.ex1

data class KotlinPersonDto private constructor(
    val name: String,
    val age: Int
) {
    /**
     * data class..
     *      값 객체로 equals, hashcode, toString, copy 를 자동으로 만들어준다.
     *      named argument 기능을 사용하면 builder 가 있는것과 같은 효과도 누릴수 있다.
     *
     * 주의..
     * data class 의 primary constructor 에 private 를 적용하면..
     * copy() 함수가 봉인된다. value object 를 복사하고 싶다면 명시적인 함수나 companion object 함수를 제공해보자..
     */

    // 경우에 따라 예외 검증은 init block 에서 한번에 처리해도 된다.

    companion object {
        fun createBaby(name: String): KotlinPersonDto {
            require(name.isNotBlank()) { "name is null or blank" } // IllegalArgumentException 이다.
            return KotlinPersonDto(name, 1)
        }

        fun create(name: String, age: Int): KotlinPersonDto {
            require(name.isNotBlank()) { "name is null or blank" }
            require(age > 0) { "age is negative" }
            return KotlinPersonDto(name, age)
        }
    }
}