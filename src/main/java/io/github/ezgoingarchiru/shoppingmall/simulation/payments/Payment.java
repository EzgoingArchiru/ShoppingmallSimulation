package io.github.ezgoingarchiru.shoppingmall.simulation.payments;

import io.github.ezgoingarchiru.shoppingmall.simulation.purchase.Purchase;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    protected User user;

    @ManyToOne
    protected Purchase purchase;



}
