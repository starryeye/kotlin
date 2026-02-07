package sub16_sealed_class

class KotlinHyundaiCarService {

    fun handle(kotlinHyundaiCar: KotlinHyundaiCar) {
        when (kotlinHyundaiCar) {
            is KotlinGrandeur -> TODO()
            is KotlinSonata -> TODO()
        }
    }

    /**
     * sealed class 를 when + is 로 활용하면
     * enum 과 마찬가지로 else 에 대한 부분을 생략할 수 있다.
     */
}