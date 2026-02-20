package io.github.ezgoingarchiru.shoppingmall.simulation.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtFilter extends OncePerRequestFilter {

    private final String secret;
    public JwtFilter(@Value("app.jwt.secret") String secret) {
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jws = request.getHeader("Authorization");

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws)
                .getBody();
        String userID = claims.getSubject();
        var teemoAuthorization = new SimpleGrantedAuthority("ROLE_TEEMO");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userID,
                null,
                List.of(teemoAuthorization)
        );
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String role = claims.get("role", String.class);
    }
}
