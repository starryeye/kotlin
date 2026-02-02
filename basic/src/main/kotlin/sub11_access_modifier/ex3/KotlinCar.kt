package sub11_access_modifier.ex3

class KotlinCar(
    internal val name: String,
    _price: Int,
) {

    var price = _price
        private set

    /**
     * name 처럼 primary constructor 에 선언하면서 접근제어자(internal) 를 적용하면..
     *      getter, setter 모두 internal 이 적용되는것이고..
     * price 처럼 custom setter 에 접근제어자(private) 를 적용하면
     *      getter 는 기본 public, setter 는 private 이 적용된다.
     */
}