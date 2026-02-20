package io.github.ezgoingarchiru.shoppingmall.simulation.users;

import io.github.ezgoingarchiru.shoppingmall.simulation.exceptions.EntityNotFoundException;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.dto.UserCreateRequest;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.dto.UserGetResponse;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.dto.UserPatchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private void checkPasswordElseThrow(String password)  {
        if(password == null || !password.matches("\\S{13,}")){
            throw new InvalidPasswordException();
        }
    }
    private void checkNicknameElseThrow(String nickname) {
        if(nickname == null || !nickname.matches("\\S{3,}")){
            throw new InvalidNicknameException();
        }
    }
    @Transactional
    public Long createUser(UserCreateRequest userCreateRequest) {
        String email = userCreateRequest.email();
        String password = userCreateRequest.password();
        String nickname = userCreateRequest.nickname();
        UserType userType = userCreateRequest.type();

        checkPasswordElseThrow(password);
        checkNicknameElseThrow(nickname);
        if(userType == null) {
            throw new InvalidUserTypeException();
        }
        String passwordHash = passwordEncoder.encode(password);

        User user = User.create(email, passwordHash, userType, nickname);
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
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #userPatchRequest.id()")
    public Long patchUser(UserPatchRequest userPatchRequest) {
        String password = userPatchRequest.password();
        String nickname = userPatchRequest.nickname();
        Long id = userPatchRequest.id();

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName(), "id=" + id));
        if(nickname != null) {
            checkNicknameElseThrow(nickname);
            user.changeNickname(nickname);
        }
        if(password != null) {
            checkPasswordElseThrow(password);
            user.changePasswordHash(passwordEncoder.encode(password));
        }
        return user.getId();
    }

    @Transactional(readOnly = true)
    public UserGetResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName(), "id=" + id));
        return UserGetResponse.from(user);
    }
    public static class InvalidPasswordException extends RuntimeException{

    }
    public static class InvalidNicknameException extends RuntimeException{

    }
    public static class InvalidUserTypeException extends RuntimeException{

    }
    public static class ExistEmailException extends RuntimeException{

    }

}
