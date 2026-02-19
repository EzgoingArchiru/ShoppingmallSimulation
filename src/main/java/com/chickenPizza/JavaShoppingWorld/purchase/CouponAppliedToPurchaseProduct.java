package com.chickenPizza.JavaShoppingWorld.purchase;

import com.chickenPizza.JavaShoppingWorld.coupons.Coupon;
import jakarta.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_coupon_apply_to_purchase_product",
                        columnNames = {"coupon_id", "purchaseProduct_id"}
                )
        }
)
public class CouponAppliedToPurchaseProduct {
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected Coupon coupon;

    @ManyToOne
    protected PurchaseProduct purchaseProduct;

}
