package io.github.ezgoingarchiru.shoppingmall.simulation.users.dto;


import io.github.ezgoingarchiru.shoppingmall.simulation.users.User;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.UserType;

public record UserGetResponse(
        Long id,
        String nickname,
        String email,
        UserType userType
) {
    public static UserGetResponse from(User user) {
        return new UserGetResponse(user.getId(), user.getNickname(), user.getEmail(), user.getType());
    }
}
