package io.github.ezgoingarchiru.shoppingmall.simulation.users.dto;

public record UserPatchRequest(
        String password,
        String nickname
){}
