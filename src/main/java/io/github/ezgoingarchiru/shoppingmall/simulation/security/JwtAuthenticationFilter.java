package io.github.ezgoingarchiru.shoppingmall.simulation.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ezgoingarchiru.shoppingmall.simulation.security.dto.AccessTokenDto;
import io.github.ezgoingarchiru.shoppingmall.simulation.security.dto.LoginDto;
import io.github.ezgoingarchiru.shoppingmall.simulation.security.dto.RefreshTokenDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer ;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginDto loginDto;
        try {
            loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDto.email(),
                    loginDto.password()
            );
            return authenticationManager.authenticate(token);
        } catch (IOException exception) {
            throw new InvalidLoginForm("로그인 형식 오류");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails)authResult.getPrincipal();
        String accessToken = jwtTokenizer.generateAccessToken(AccessTokenDto.from(userDetails));
        String refreshToken = jwtTokenizer.generateRefreshToken(RefreshTokenDto.from(userDetails));
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);
    }
    static class InvalidLoginForm extends AuthenticationException{
        public InvalidLoginForm(String msg) {
            super(msg);
        }
    }
}