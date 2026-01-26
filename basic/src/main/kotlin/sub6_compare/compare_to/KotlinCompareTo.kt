package sub6_compare.compare_to

fun main() {

    val account1 = Account(2_000L)
    val account2 = Account(1_000L)

    val account3 = account1
    val account4 = Account(2_000L)

    /**
     * Kotlin 에서는 객체간 비교에 비교 연산자를 사용하면 자동으로 CompareTo 를 호출해준다.
     */
    if (account1 > account2) {
        println("Account1 is greater than Account2")
    }

    /**
     * Kotlin 에서는 객체의 동일성 비교 시
     *      === 연산자를 사용하면 된다.
     */
    if (account1 === account3) {
        println("account1 === account3, ${account1 === account3}")
    }

    /**
     * Kotlin 에서는 객체의 동등성 비교 시
     *      == 연산자를 사용하면 된다. (내부적으로 equals 메서드를 호출한다.)
     * 물론, 해당 객체가 Java 객체이면 equals 메서드가 오버라이딩된 상태여야하고
     * Kotlin 객체라도 equals 메서드가 오버라이딩된 상태여야한다. (data class 면 자동으로 equals 구현됨)
     */
    if (account1 == account4) {
        println("account1 == account4, ${account1 == account4}")
    }

    /**
     * Kotlin에서는 연산자 오버로딩을 지원하며,
     *      "+" 연산자는 내부적으로 `operator fun plus()` 메서드를 호출한다.
     *
     * 즉, "account1 + account4"는 "account1.plus(account4)"와 동일하게 동작한다.
     */
    println("account1 + account4, ${account1 + account4}")
}

private data class Account(
    val amount: Long
): Comparable<Account> {

    override fun compareTo(other: Account): Int { // 파라미터에 nullable 타입이 아니게 하여 @NotNull 이 필요 없음
        return amount.compareTo(other.amount) // Long.compare() 에 대응됨
    }

    operator fun plus(other: Account): Account = // "+" 연산자 오버로딩
        Account(amount + other.amount)
}