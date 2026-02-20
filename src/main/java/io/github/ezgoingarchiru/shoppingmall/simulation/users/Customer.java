package io.github.ezgoingarchiru.shoppingmall.simulation.users;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    @Id
    @Column(name = "user_id")
    protected Long userId;

    @MapsId("userId")
    @OneToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(nullable = false)
    protected Long point;

}
