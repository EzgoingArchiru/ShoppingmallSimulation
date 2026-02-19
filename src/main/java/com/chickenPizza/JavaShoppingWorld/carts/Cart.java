package com.chickenPizza.JavaShoppingWorld.carts;

import com.chickenPizza.JavaShoppingWorld.users.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected User user;

    @CreationTimestamp
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    protected LocalDateTime updatedAt;
}
