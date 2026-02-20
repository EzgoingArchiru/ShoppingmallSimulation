package io.github.ezgoingarchiru.shoppingmall.simulation.coupons;

import jakarta.persistence.*;

@Entity
@Table
public class AmountCoupon {

    @Id
    @Column(name = "coupon_id")
    protected Long couponId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "coupon_id")
    protected Coupon coupon;

    protected Long amountPrice;
}
