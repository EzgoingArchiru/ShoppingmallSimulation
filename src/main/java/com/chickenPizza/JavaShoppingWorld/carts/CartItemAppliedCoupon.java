package com.chickenPizza.JavaShoppingWorld.carts;

import com.chickenPizza.JavaShoppingWorld.coupons.Coupon;
import jakarta.persistence.*;

@Entity
@Table
public class CartItemAppliedCoupon {
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected CartItem cartItem;
    @ManyToOne
    protected Coupon coupon;
}
