package dev.starryeye.book_library.application.user.command;

import dev.starryeye.book_library.application.user.command.command.RegisterUserCommand;
import dev.starryeye.book_library.application.user.command.result.RegisterUserResult;
import dev.starryeye.book_library.domain.user.User;
import dev.starryeye.book_library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class RegisterUserService {

    private final UserRepository userRepository;

    public RegisterUserResult register(RegisterUserCommand command) {

        User user = User.createUser(
                command.username(),
                command.age()
        );

        userRepository.save(user);

        return RegisterUserResult.of(user);
    }
}
