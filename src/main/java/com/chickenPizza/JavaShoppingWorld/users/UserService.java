package com.chickenPizza.JavaShoppingWorld.users;

import com.chickenPizza.JavaShoppingWorld.users.dto.UserCreateRequest;
import com.chickenPizza.JavaShoppingWorld.users.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public Long createUser(UserCreateRequest userCreateRequest) {
        String email = userCreateRequest.email();
        String password = userCreateRequest.password();
        String nickname = userCreateRequest.nickname();
        UserType userType = userCreateRequest.type();

        if(password == null || !password.matches("\\S{13,}")){
            throw new InvalidPasswordException();
        }

        String passwordHash;
        passwordHash = passwordEncoder.encode(password);
        User user = UserFactory.createNewUser(email, passwordHash, userType, nickname);
        try {
            userRepository.save(user);
        } catch(DataIntegrityViolationException e) {
            throw new ExistEmailException();
        }
        catch (Exception e) {
            throw new IllegalTransactionStateException("fail save", e);
        }
        return user.getId();
    }
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public Long updateUser(UserUpdateRequest userUpdateRequest) {
        String password = userUpdateRequest.password();
        String nickname = userUpdateRequest.nickname();
        Long id = userUpdateRequest.id();

        User user =  userRepository.findById(id).orElseThrow();
        if(nickname != null) {
            user.changeNickname(nickname);
        }
        if(password != null) {
            user.changePasswordHash(passwordEncoder.encode(password));
        }
        userRepository.save(user);
        return user.getId();
    }
    public static class InvalidPasswordException extends RuntimeException{

    }
    public static class InvalidUserTypeException extends RuntimeException{

    }
    public static class ExistEmailException extends RuntimeException{

    }

}
