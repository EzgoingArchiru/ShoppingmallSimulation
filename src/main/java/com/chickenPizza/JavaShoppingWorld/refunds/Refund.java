package com.chickenPizza.JavaShoppingWorld.refunds;
import com.chickenPizza.JavaShoppingWorld.payments.Payment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "refunds")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문/결제 식별자 (nullable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(nullable = false)
    private Long amount;

    @Column(name = "is_partial", nullable = false)
    private boolean isPartial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefundStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefundMethod method;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Refund(Payment payment, Long amount, boolean isPartial, RefundMethod method) {
        this.payment = payment;
        this.amount = amount;
        this.isPartial = isPartial;
        this.method = method;
        this.status = RefundStatus.REFUND_PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void markRefunded() {
        this.status = RefundStatus.REFUNDED;
        this.updatedAt = LocalDateTime.now();
    }

    public void markFailed() {
        this.status = RefundStatus.FAILED;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = RefundStatus.CANCELED;
        this.updatedAt = LocalDateTime.now();
    }

}