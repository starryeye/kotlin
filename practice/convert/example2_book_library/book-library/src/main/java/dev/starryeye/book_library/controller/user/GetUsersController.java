package dev.starryeye.book_library.controller.user;

import dev.starryeye.book_library.application.user.query.GetUsersService;
import dev.starryeye.book_library.application.user.query.result.GetUsersResult;
import dev.starryeye.book_library.controller.user.response.UsersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetUsersController {

    private final GetUsersService service;

    @GetMapping("/api/v1/users")
    public ResponseEntity<UsersResponse> getUsers() {
        GetUsersResult result = service.get();
        return ResponseEntity.ok(UsersResponse.of(result));
    }
}
