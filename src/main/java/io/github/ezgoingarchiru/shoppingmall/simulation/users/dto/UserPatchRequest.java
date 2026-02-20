package io.github.ezgoingarchiru.shoppingmall.simulation.users.dto;

public record UserPatchRequest(
        Long id,
        String password,
        String nickname
){}
