package dev.starryeye.book_library.application.command;

import dev.starryeye.book_library.application.command.command.RegisterUserCommand;
import dev.starryeye.book_library.application.command.result.RegisterUserResult;
import dev.starryeye.book_library.domain.User;
import dev.starryeye.book_library.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
