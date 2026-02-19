package com.chickenPizza.JavaShoppingWorld.users.dto;

import com.chickenPizza.JavaShoppingWorld.users.UserType;

public record UserUpdateRequest(
        Long id,
        String password,
        String nickname
){}
