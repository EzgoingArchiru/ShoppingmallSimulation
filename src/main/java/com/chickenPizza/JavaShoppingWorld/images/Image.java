package com.chickenPizza.JavaShoppingWorld.images;

import com.chickenPizza.JavaShoppingWorld.users.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
public class Image {
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected User user;

    @CreationTimestamp
    protected LocalDateTime createdAt;
}
