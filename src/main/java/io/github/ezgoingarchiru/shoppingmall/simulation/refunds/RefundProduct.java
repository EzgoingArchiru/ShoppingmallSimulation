package io.github.ezgoingarchiru.shoppingmall.simulation.refunds;
import io.github.ezgoingarchiru.shoppingmall.simulation.purchase.PurchaseProduct;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "refund_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefundProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 환불은 반드시 존재
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "refund_id", nullable = false)
    private Refund refund;

    // 환불 대상 구매 라인아이템은 반드시 존재
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_product_id", nullable = false)
    private PurchaseProduct purchaseProduct;

    @Column(nullable = false)
    private Integer quantity;

    public RefundProduct(Refund refund, PurchaseProduct purchaseProduct, Integer quantity) {
        this.refund = refund;
        this.purchaseProduct = purchaseProduct;
        this.quantity = quantity;
    }
}