package sub5_type_casting

fun main() {

    // 코틀린에서는 암시적 형변환이 불가능하다.

    val number3: Int = 3

    val number4: Long
    val number5: Long
    val number6: Long

//    number4 = number3 // compile error
    number5 = number3.toLong() // 명시적 형변환 해줘야한다.
    number6 = number3 as Long // 명시적 형변환 해줘야한다.



    //-----------------------------------------------------------------


    /**
     * Java 의 instanceof 와 비슷한 is
     *      A is B
     *           A 인스턴스가 B 타입이면 true
     *           A 인스턴스가 B 타입이 아니라면 false
     *
     *      A !is B
     *           A 인스턴스가 B 타입이면 false
     *           A 인스턴스가 B 타입이 아니라면 true
     *
     * 명시적 형변환
     *      A as B
     *          A 인스턴스를 B 타입으로 명시적 형변환 가능하면 캐스팅됨
     *          A 인스턴스를 B 타입으로 명시적 형변환 불가능하면 예외발생
     *      A as? B
     *          A 인스턴스를 B 타입으로 명시적 형변환 가능하면 캐스팅됨
     *          A 가 null 이면 null 이 된다.
     *          A 인스턴스를 B 타입으로 명시적 형변환 불가능하면 null 이 된다.
     *
     *
     */
}