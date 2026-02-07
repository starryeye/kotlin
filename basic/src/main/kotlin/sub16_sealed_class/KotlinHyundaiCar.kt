package sub16_sealed_class

sealed class KotlinHyundaiCar(
    val name: String,
    var price: Double
)

/**
 * KotlinHyundaiCar 는 sealed class 로 선언됨.
 *      -> 동일 패키지의 클래스만 KotlinHyundaiCar 을 상속할 수 있다. (하위 패키지여도 불가능임)
 */