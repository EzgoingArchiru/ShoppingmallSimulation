package io.github.ezgoingarchiru.shoppingmall.simulation.carts;

import io.github.ezgoingarchiru.shoppingmall.simulation.coupons.Coupon;
import io.github.ezgoingarchiru.shoppingmall.simulation.products.Product;
import jakarta.persistence.*;

@Entity
@Table
public class CartItem {
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected Cart cart;

    @ManyToOne
    protected Product product;

    protected Integer quantity;

    @ManyToOne
    protected Coupon coupon;
}
