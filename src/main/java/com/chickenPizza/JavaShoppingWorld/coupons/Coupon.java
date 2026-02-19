package com.chickenPizza.JavaShoppingWorld.coupons;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
public class Coupon {
    @Id
    @GeneratedValue
    protected Long id;

    protected String name;

    protected Long minNeedPrice;

    protected Long usageLimit;

    protected Long perUserLimit;

    protected Boolean isStackable;

    protected LocalDateTime startAt;

    protected LocalDateTime endAt;

    @CreationTimestamp
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    protected LocalDateTime updatedAt;


}

