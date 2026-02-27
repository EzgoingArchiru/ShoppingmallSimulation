package io.github.ezgoingarchiru.shoppingmall.simulation.users;

import io.github.ezgoingarchiru.shoppingmall.simulation.users.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> register(@Valid @RequestBody UserCreateRequest req) {
        Long id = userService.createUser(req);
        return ResponseEntity
                .created(URI.create("/users/" + id))
                .body(new UserCreateResponse(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Positive Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void>  patchUser(@PathVariable @Positive Long id, @Valid @RequestBody UserPatchRequest req) {
        userService.patchUser(id, req);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponse> getUser(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}