package sub9_enum

enum class PaymentStatus2(val label: String) : Payable {
    UNPAID("미지급") {
        override fun isPayable() = true
    },
    PAID("지급완료") {
        override fun isPayable() = false
    },
    FAILED("지급실패") {
        override fun isPayable() = false
    }; // 프로퍼티와 함수 사이에 구분을 위해 세미콜론
}

interface Payable {
    fun isPayable(): Boolean
}

fun main() {

    val valueOf = PaymentStatus2.valueOf("PAID") //valueOf 를 통해 인스턴스를 만들 수 있다. Java 도 가능
    println(valueOf.label)

    if (valueOf == PaymentStatus2.PAID) {
        println("동등성 검사")
    }

    for (status in PaymentStatus2.values()) {
        println("[${status.ordinal}] ${status.name} 은 ${status.label} 이다.")
    }
}