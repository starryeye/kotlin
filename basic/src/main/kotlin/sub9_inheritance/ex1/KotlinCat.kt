package sub9_inheritance.ex1

class KotlinCat(
    species: String,
) : KotlinAnimal(species, 4) {

    /**
     * KotlinCat 은 KotlinAnimal 을 상속함. "{상속 받는 클래스} : {상속할 클래스}"
     * KotlinCat 의 생성자에서 species 는 val, var 키워드를 사용하지 않았으므로 생성자 파라미터이다. (프로퍼티 선언이 아님)
     * species 변수를 KotlinAnimal() 에 넣어줌
     */

    /**
     * override 키워드를 통해 부모 클래스 함수를 오버라이딩할 수 있다.
     */
    override fun move() {
        println("Kotlin Cat")
    }
}