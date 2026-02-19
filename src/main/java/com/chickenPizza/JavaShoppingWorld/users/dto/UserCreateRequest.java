package com.chickenPizza.JavaShoppingWorld.users.dto;

import com.chickenPizza.JavaShoppingWorld.users.UserType;

public record UserCreateRequest(
        Long id,
        String email,
        String password,
        String nickname,
        UserType type
){}
