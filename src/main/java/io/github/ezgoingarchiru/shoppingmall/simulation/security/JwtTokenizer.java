package io.github.ezgoingarchiru.shoppingmall.simulation.security;

import io.github.ezgoingarchiru.shoppingmall.simulation.security.dto.AccessTokenDto;
import io.github.ezgoingarchiru.shoppingmall.simulation.security.dto.RefreshTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtTokenizer {
    private final SecretKey secretKey;
    private final Long accessTokenExpirationSeconds;
    private final Long refreshTokenExpirationSeconds;
    private final String issuer;
    public JwtTokenizer(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.issuer}") String issuer,
            @Value("${app.jwt.access-token-expiration-seconds}") Long accessTokenExpirationSeconds,
            @Value("${app.jwt.refresh-token-expiration-seconds}") Long refreshTokenExpirationSeconds) {
        this.accessTokenExpirationSeconds = accessTokenExpirationSeconds;
        this.refreshTokenExpirationSeconds = refreshTokenExpirationSeconds;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.issuer = issuer;
    }
    public static class ACCESS_TOKEN_CLAIMS {
        public static final String GRANT_TYPE = "grant";
        public static final String EMAIL = "email";
        public static final String USER_TYPE = "type";
        public static final String ID = "sub"; //<-sub
    }
    public static class REFRESH_TOKEN_CLAIMS {
        public static final String ID = "sub"; //<-sub
    }

    protected SecretKey getKey() {
        return this.secretKey;
    }
    public String generateAccessToken(AccessTokenDto accessTokenDto) {
        return Jwts.builder()
                .issuer(issuer)
                .subject(accessTokenDto.id().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpirationSeconds * 1000))
                .claim(ACCESS_TOKEN_CLAIMS.GRANT_TYPE, accessTokenDto.grantType().name())
                .claim(ACCESS_TOKEN_CLAIMS.USER_TYPE, accessTokenDto.userType().name())
                .claim(ACCESS_TOKEN_CLAIMS.EMAIL, accessTokenDto.email())
                .signWith(getKey())
                .compact();

    }
    public String generateRefreshToken(RefreshTokenDto refreshTokenDto) {
        return Jwts.builder()
                .issuer(issuer)
                .subject(refreshTokenDto.id().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpirationSeconds * 1000))
                .signWith(getKey())
                .compact();

    }
    public CustomUserDetails verify(String jws) throws IllegalArgumentException{
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(jws)
                    .getPayload();
            AccessTokenDto accessTokenDto = AccessTokenDto.from(claims);
            return CustomUserDetails.from(accessTokenDto);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

}