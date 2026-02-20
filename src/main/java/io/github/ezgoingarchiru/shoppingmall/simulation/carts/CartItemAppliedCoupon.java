package io.github.ezgoingarchiru.shoppingmall.simulation.carts;

import io.github.ezgoingarchiru.shoppingmall.simulation.coupons.Coupon;
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
