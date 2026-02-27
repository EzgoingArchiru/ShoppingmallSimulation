package io.github.ezgoingarchiru.shoppingmall.simulation.security;

import io.github.ezgoingarchiru.shoppingmall.simulation.users.User;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("NOT FOUND");
        }
        return CustomUserDetails.from(user);
    }
}

