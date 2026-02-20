package dev.starryeye.book_library.application.user.query;

import dev.starryeye.book_library.application.user.query.result.GetUsersResult;
import dev.starryeye.book_library.domain.user.User;
import dev.starryeye.book_library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetUsersService {

    private final UserRepository userRepository;

    public GetUsersResult get() {

        List<User> users = userRepository.findAll();

        return GetUsersResult.of(users);
    }
}
