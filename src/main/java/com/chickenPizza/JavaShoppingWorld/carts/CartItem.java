package com.chickenPizza.JavaShoppingWorld.carts;

import com.chickenPizza.JavaShoppingWorld.coupons.Coupon;
import com.chickenPizza.JavaShoppingWorld.products.Product;
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
