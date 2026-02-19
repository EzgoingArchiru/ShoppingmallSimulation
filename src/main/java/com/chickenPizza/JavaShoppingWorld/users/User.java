package com.chickenPizza.JavaShoppingWorld.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true, length = 255)
    @Email
    @NotBlank
    protected String email;

    @Column(nullable = false, length = 255)
    protected String passwordHash;
    public void changePasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    @Column(nullable = false, length = 255, unique = true)
    @Size(min = 5, max = 255)
    protected String nickname;
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected UserType type;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    protected LocalDateTime updatedAt;

}
