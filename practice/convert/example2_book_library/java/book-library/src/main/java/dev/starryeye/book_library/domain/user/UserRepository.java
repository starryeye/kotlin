package dev.starryeye.book_library.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // LoanHistories 즉시 로딩
    @Query("""
        select u
        from User u
        left join fetch u.loanHistories
        where u.id = :userId
    """)
    Optional<User> findByIdWithLoanHistories(@Param("userId") Long userId);
}
