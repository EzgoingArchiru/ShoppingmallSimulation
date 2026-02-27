package io.github.ezgoingarchiru.shoppingmall.simulation.security.dto;

import io.github.ezgoingarchiru.shoppingmall.simulation.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record RefreshTokenDto(
        Long id
) {
    public static RefreshTokenDto from(CustomUserDetails userDetails) {
        return RefreshTokenDto
                .builder()
                .id(userDetails.getId())
                .build();
    }
    public static RefreshTokenDto from(Claims claims) {
        return RefreshTokenDto
                .builder()
                .id(Long.parseLong(claims.getSubject()))
                .build();
    }
}
