package io.github.ezgoingarchiru.shoppingmall.simulation.users;

import io.github.ezgoingarchiru.shoppingmall.simulation.users.dto.UserCreateRequest;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.dto.UserCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserCreateResponse> register(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        Long id = userService.createUser(userCreateRequest);
        return ResponseEntity
                .created(URI.create("/users/" + id))
                .body(new UserCreateResponse(id));
    }
}