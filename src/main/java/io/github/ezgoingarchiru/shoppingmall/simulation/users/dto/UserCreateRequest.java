package io.github.ezgoingarchiru.shoppingmall.simulation.users.dto;

import io.github.ezgoingarchiru.shoppingmall.simulation.users.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 13)
        String password,

        @NotBlank
        @Size (min = 3)
        String nickname,
        
        @NotNull
        UserType type
){}
