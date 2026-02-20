package io.github.ezgoingarchiru.shoppingmall.simulation.coupons;

import io.github.ezgoingarchiru.shoppingmall.simulation.products.ProductCategory;
import jakarta.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_applicable_coupon_category",
                        columnNames = {"coupon_id", "product_category_id"}
                )
        }
)
public class ApplicableCouponCategory {
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected Coupon coupon;

    @ManyToOne
    protected ProductCategory productCategory;
}
