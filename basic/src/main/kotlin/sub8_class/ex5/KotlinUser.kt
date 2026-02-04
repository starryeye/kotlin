package sub8_class.ex5

fun main() {

}

/**
 * data class + public 프로퍼티
 *
 * - 값 객체(Value Object)
 * - equals / hashCode / toString / copy 자동 생성
 * - DTO, 요청/응답 모델, 설정 객체에 최적
 *
 * 👉 "이 객체는 데이터 그 자체가 의미다" 라는 의도가 명확
 */
data class KotlinUser1(
    val name: String,
    val age: Int
)


/**
 * data class + private 프로퍼티
 *
 * - data class의 장점(copy, componentN 등)을 거의 활용 불가
 * - 외부에서 데이터 접근도 불가
 *
 * data class의 의도(값 객체)와 캡슐화가 충돌
 * 특별한 이유가 없으면 사용하지 않음
 */
data class KotlinUser2(
    private val name: String,
    private val age: Int
)

/**
 * 일반 class + public 프로퍼티
 *
 * - equals는 참조 비교
 * - 행위(비즈니스 로직)를 추가하기 좋음
 *
 * "같은 값이어도 다른 객체면 다른 존재"라는 개념
 * Entity, Aggregate Root 등에 사용
 */
class KotlinUser3(
    val name: String,
    val age: Int
)

/**
 * spring bean 으로 의미가 있음. 예시는 name, age 이지만... repository 라 생각하자.
 */
class KotlinUser4(
    private val name: String,
    private val age: Int
)


/**
 * private constructor + factory method
 *
 * - 객체 생성 규칙을 강제할 수 있음
 * - 불변 객체 + 유효성 검증 가능
 *
 * "객체는 항상 올바른 상태로만 생성된다"를 보장
 * Java Builder 대신 Kotlin에서 많이 쓰는 패턴
 */
class KotlinUser5 private constructor(
    val name: String,
    val age: Int
) {
    companion object {
        fun create(name: String, age: Int): KotlinUser5 {
            require(age >= 0)
            return KotlinUser5(name, age)
        }
    }
}


/**
 * private constructor + private 프로퍼티
 *
 * - 내부 상태 완전 은닉
 * - 의미 있는 행위만 외부에 노출
 *
 * 외부에서는 "User가 무엇을 할 수 있는지"만 알 수 있음
 * 강한 캡슐화가 필요한 핵심 도메인에서 사용
 */
class KotlinUser6 private constructor(
    private val name: String,
    private val age: Int
) {
    companion object {
        fun create(name: String, age: Int): KotlinUser6 {
            require(age >= 0)
            return KotlinUser6(name, age)
        }
    }
}