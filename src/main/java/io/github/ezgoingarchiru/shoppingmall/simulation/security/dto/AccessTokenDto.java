package io.github.ezgoingarchiru.shoppingmall.simulation.security.dto;

import io.github.ezgoingarchiru.shoppingmall.simulation.security.CustomUserDetails;
import io.github.ezgoingarchiru.shoppingmall.simulation.security.JwtTokenizer;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.GrantType;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.UserType;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AccessTokenDto(
        Long id, //id
        GrantType grantType,
        UserType userType,
        String email
) {
    public static AccessTokenDto from(Claims claims) {
        return AccessTokenDto
                .builder()
                .id(Long.parseLong(claims.getSubject()))
                .grantType(GrantType.valueOf(claims.get(JwtTokenizer.ACCESS_TOKEN_CLAIMS.GRANT_TYPE, String.class)))
                .userType(UserType.valueOf(claims.get(JwtTokenizer.ACCESS_TOKEN_CLAIMS.USER_TYPE, String.class)))
                .email(claims.get(JwtTokenizer.ACCESS_TOKEN_CLAIMS.EMAIL, String.class))
                .build();
    }
    public static AccessTokenDto from(CustomUserDetails userDetails) {
        return AccessTokenDto
                .builder()
                .id(userDetails.getId())
                .grantType(userDetails.getGrantType())
                .userType(userDetails.getUserType())
                .email(userDetails.getEmail())
                .build();
    }
}
