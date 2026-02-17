package dev.starryeye.book_library.application.command;

import dev.starryeye.book_library.application.command.command.ChangeUsernameCommand;
import dev.starryeye.book_library.domain.User;
import dev.starryeye.book_library.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ChangeUsernameService {

    private final UserRepository userRepository;

    public void change(ChangeUsernameCommand command) {

        User user = userRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("user is not found, id = " + command.id()));

        user.changeUsername(command.username());
    }
}
