package dev.starryeye.book_library.application.command;

import dev.starryeye.book_library.domain.user.User;
import dev.starryeye.book_library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class WithdrawUserService {

    private final UserRepository userRepository;

    public void withdraw(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user is not found, id = " + id));

        userRepository.delete(user);
    }
}
