package sub10_interface.ex1.impl

import sub10_interface.ex1.KotlinAnimal
import sub10_interface.ex1.KotlinFlyable
import sub10_interface.ex1.KotlinSwimmable

class KotlinPenguin(
    species: String
) : KotlinAnimal(species, 2), KotlinSwimmable, KotlinFlyable {
    // Kotlin 에서 interface 를 구현할 때는 ":" 뒤에 상속하는 클래스와 구분없이 나열해준다.


    val wingCount: Int = 2

    override fun move() {
        println("Kotlin Penguin")
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount

    override fun fly() { // Kotlin 에서 인터페이스 추상메서드를 구현하는것은 별다를바 없음
        println("Kotlin Penguin Flying")
    }

    override fun act() { // Kotlin 에서 인터페이스 default 메서드를 구현하는 것은 별다를바 없음
        super<KotlinSwimmable>.act()
        super<KotlinFlyable>.act()
    }
    /**
     * 구체 클레스에서 여러 인터페이스를 구현할 때,
     * 인터페이스를 특정할때는..
     * super<타입>.함수명 을 사용하면 된다.
     */
}