package io.github.ezgoingarchiru.shoppingmall.simulation.deliveries;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
public class Delivery {
    @Id
    @GeneratedValue
    protected Long id;

    @Enumerated(EnumType.STRING)
    protected DeliveryStatus status;

    @CreationTimestamp
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    protected LocalDateTime updatedAt;

    protected LocalDateTime deliveredAt;
}

