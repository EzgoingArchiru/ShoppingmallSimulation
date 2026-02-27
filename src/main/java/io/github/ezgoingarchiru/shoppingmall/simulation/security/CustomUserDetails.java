package io.github.ezgoingarchiru.shoppingmall.simulation.security;

import io.github.ezgoingarchiru.shoppingmall.simulation.security.dto.AccessTokenDto;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.GrantType;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.User;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final String passwordHash;
    @Getter
    private final Long id;
    @Getter
    private final UserType userType;
    @Getter
    private final GrantType grantType;
    @Getter
    private final String email;
    private final List<GrantedAuthority> grantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    protected static List<GrantedAuthority> getGrantedAuthorities(GrantType grantType, UserType userType) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + grantType.name()));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userType.name()));
        return grantedAuthorities;
    }
    public static CustomUserDetails from(User user) {
        return CustomUserDetails.builder()
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .grantedAuthorities(getGrantedAuthorities(user.getGrantType(), user.getType()))
                .id(user.getId())
                .userType(user.getType())
                .grantType(user.getGrantType())
                .email(user.getEmail())
                .build();
    }
    public static CustomUserDetails from(AccessTokenDto accessTokenDto) {
        return CustomUserDetails.builder()
                .email(accessTokenDto.email())
                .passwordHash(null)
                .grantedAuthorities(getGrantedAuthorities(accessTokenDto.grantType(), accessTokenDto.userType()))
                .id(accessTokenDto.id())
                .userType(accessTokenDto.userType())
                .grantType(accessTokenDto.grantType())
                .build();
    }


}
