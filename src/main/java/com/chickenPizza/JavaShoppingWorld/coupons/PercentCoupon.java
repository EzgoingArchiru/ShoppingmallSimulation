package com.chickenPizza.JavaShoppingWorld.coupons;

import jakarta.persistence.*;

@Entity
@Table
public class PercentCoupon {

    @Id
    @Column(name = "coupon_id")
    protected Long couponId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "coupon_id")
    protected Coupon coupon;

    protected Integer percent;

    protected Long maxDiscountPrice;
}
