package dev.starryeye.book_library.domain.user.loan_history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    boolean existsByBookIdAndIsReturn(Long bookId, boolean isReturn);
}
