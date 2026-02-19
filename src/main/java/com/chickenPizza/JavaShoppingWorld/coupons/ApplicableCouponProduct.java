package com.chickenPizza.JavaShoppingWorld.coupons;

import com.chickenPizza.JavaShoppingWorld.products.Product;
import jakarta.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_applicable_coupon_product",
                        columnNames = {"coupon_id", "product_id"}
                )
        }
)
public class ApplicableCouponProduct {
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected Coupon coupon;

    @ManyToOne
    protected Product product;
}
