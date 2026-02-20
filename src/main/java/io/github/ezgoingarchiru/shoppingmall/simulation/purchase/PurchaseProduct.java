package io.github.ezgoingarchiru.shoppingmall.simulation.purchase;

import io.github.ezgoingarchiru.shoppingmall.simulation.products.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "purchase_product",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_purchase_product",
                        columnNames = {"purchase_id", "product_id"}
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문은 반드시 존재
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    // 상품은 삭제될 수 있음 → nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // 스냅샷 데이터
    @Column(name = "final_price")
    private Long finalPrice;

    @Column(name = "original_price", nullable = false)
    private Long originalPrice;

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    @Column(nullable = false)
    private Integer quantity;

    public PurchaseProduct(Purchase purchase,
                           Product product,
                           Long originalPrice,
                           Long finalPrice,
                           String productName,
                           Integer quantity) {
        this.purchase = purchase;
        this.product = product;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.productName = productName;
        this.quantity = quantity;
    }
}
