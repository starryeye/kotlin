package sub9_inheritance.ex1

abstract class KotlinAnimal( // 추상클래스는 abstract 키워드
    protected val species: String,
    protected open val legCount: Int // open 은 KotlinPenguin 참고
) {

    abstract fun move(): Unit // 추상메서드는 abstract 키워드, Unit 생략가능

}