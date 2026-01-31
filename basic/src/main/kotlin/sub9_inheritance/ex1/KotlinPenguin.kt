package sub9_inheritance.ex1

class KotlinPenguin(
    species: String,
) : KotlinAnimal(species, 2) {

    private val wingCount: Int = 2

    override fun move() {
        println("Kotlin Penguin")
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount

    /**
     * Java 에서는..
     * JavaAnimal 의 getLegCount() 메서드를 JavaPenguin 에서 오버라이딩함.
     *
     * Kotlin 에서는..
     * KotlinAnimal 의 protected val legCount: Int 프로퍼티 자체를 오버라이딩해야함.
     *      이후, custom getter 를 이용
     * 대신.. 프로퍼티를 오버라이딩 하려면, KotlinAnimal 의 legCount 프로퍼티에 open 키워드를 사용해줘야한다.
     *
     * 참고.
     * 아니 그냥.. 코틀린에서도 getLegCount() 함수만 오버라이딩 할수는 없나?
     * ->   없다. 코틀린에서 protected val legCount: Int 이 선언은
     *      두 개를 동시에 의미 (프로퍼티선언 + getter)
     *      즉, 독립적인게 아님.
     *      Kotlin에서는 “getter/setter는 프로퍼티의 일부”이며, 오버라이딩은 항상 “프로퍼티 단위”로 한다.
     *      물론,
     *          KotlinAnimal 에서 abstract fun getLegCount(): Int 를 선언해두어도 되지만,
     *          penguin.legCount 를 호출하지말고 penguin.getLegCount() 로 호출해야하는 이상한 상황이 될 것이다.
     */
}