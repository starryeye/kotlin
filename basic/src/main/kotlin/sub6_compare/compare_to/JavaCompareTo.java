package sub6_compare.compare_to;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class JavaCompareTo {

    public static void main(String[] args) {
        Account account1 = new Account(2_000L);
        Account account2 = new Account(1_000L);

        Account account3 = account1;
        Account account4 = new Account(2_000L);

        /**
         * Java 에서의 객체간 비교
         */
        if (account1.compareTo(account2) > 0) {
            System.out.println("Account1 is greater than Account2");
        }


        /**
         * Java 에서의 동일성 비교
         */
        if (account1 == account3) {
            System.out.println("account1 == account3, " + (account1 == account3));
        }

        /**
         * Java 에서의 동등성 비교
         */
        if (account1.equals(account4)) {
            System.out.println("account1.equals(account4), " + (account1.equals(account4)));
        }

        /**
         * Java 에서의 객체간 연산은 직접 메서드를 구현해야함
         */
        System.out.println("account1.plus(account4), " + account1.plus(account4));
    }

    private static class Account implements Comparable<Account> {

        private final long amount;

        public Account(long amount) {
            this.amount = amount;
        }

        public Account plus(Account other) {
            return new Account(amount + other.amount);
        }

        @Override
        public int compareTo(@NotNull Account o) {
            return Long.compare(this.amount, o.amount);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Account account = (Account) o;
            return amount == account.amount;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(amount);
        }

        @Override
        public String toString() {
            return "Account{" +
                    "amount=" + amount +
                    '}';
        }
    }
}
