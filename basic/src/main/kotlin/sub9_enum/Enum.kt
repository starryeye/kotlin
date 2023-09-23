package sub9_enum

/**
 * 코틀린에서의 enum class 를 알아본다.
 *
 * enum class 도 클래스이므로
 * 생성자, 프로퍼티, 함수를 선언해줄 수 있다.
 *
 */
enum class PaymentStatus(val label: String) {
    UNPAID("미지급") {
        override fun isPayable() = true
    },
    PAID("지급완료") {
        override fun isPayable() = false
    },
    FAILED("지급실패") {
        override fun isPayable() = false
    }

    ; // 프로퍼티와 함수 사이에 구분을 위해 세미콜론

    abstract fun isPayable(): Boolean // 추상 함수이므로 각 상태에서 구현이 필요하다.
}

fun main() {
    println(PaymentStatus.PAID) //PAID 출력
    println(PaymentStatus.PAID.label) //지급완료 출력
    println(PaymentStatus.PAID.name) //PAID 출력
    println(PaymentStatus.PAID.ordinal) //1 출력 (선언 순서)

    if(PaymentStatus.UNPAID.isPayable()) {
        println("결제 가능 상태")
    }
}