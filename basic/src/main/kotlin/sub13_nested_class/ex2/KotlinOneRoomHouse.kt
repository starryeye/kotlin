package sub13_nested_class.ex2

class KotlinOneRoomHouse(
    private val address: String,
    private val room: Room
) {

    /**
     * 코틀린에서 inner class 는 아래와 같이 사용할 수 있다.
     *
     * inner class 로 표기 해야하며
     * 내부 클래스에서 외부 클래스 인스턴스에 참조할 때는
     *      "this@외부클래스타입." 으로 접근할 수 있다.
     *
     * 참고
     * inner class 를 사용하지 말고 ex1 처럼 사용하자.
     */
    inner class Room(
        private val area: Double,
    ) {
        val address: String
            get() = this@KotlinOneRoomHouse.address // 외부 클래스 인스턴스를 접근. (내부 클래스가 아니였다면 field.address 였다.)
    }
}