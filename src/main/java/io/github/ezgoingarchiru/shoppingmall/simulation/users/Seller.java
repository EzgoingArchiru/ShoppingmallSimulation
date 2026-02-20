package io.github.ezgoingarchiru.shoppingmall.simulation.users;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sellers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller {
    @Id
    @Column(name = "user_id")
    protected Long userId;

    @MapsId("userId")
    @OneToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(nullable = false)
    protected String businessId;

    @Column(nullable = false)
    protected String businessName;
}
