package dev.starryeye.book_library.application.user.query;

import dev.starryeye.book_library.domain.user.User;
import dev.starryeye.book_library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetUserService {

    private final UserRepository userRepository;

    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user is not found, id = " + id));
    }
}
