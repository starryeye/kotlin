package dev.starryeye.book_library.application.user.command;

import dev.starryeye.book_library.domain.user.User;
import dev.starryeye.book_library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ReturnBookService {

    private final UserRepository userRepository;

    public void returnBook(Long userId, Long bookId) {

        User user = userRepository.findByIdWithLoanHistories(userId)
                .orElseThrow(() -> new IllegalArgumentException("user is not found, id = " + userId));

        user.returnBook(bookId);
    }
}
