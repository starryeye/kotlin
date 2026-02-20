package dev.starryeye.book_library.application.user.command;

import dev.starryeye.book_library.domain.user.User;
import dev.starryeye.book_library.domain.user.UserRepository;
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class LoanBookService {

    private final UserRepository userRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;

    public void loan(Long userId, Long bookId) {
        if (userLoanHistoryRepository.existsByBookIdAndIsReturn(bookId, false)) {
            throw new IllegalStateException("book is already loaned, id = " + bookId);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user is not found, id = " + userId));

        user.loanBook(bookId);
    }
}
