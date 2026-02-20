package io.github.ezgoingarchiru.shoppingmall.simulation.purchase;

import io.github.ezgoingarchiru.shoppingmall.simulation.users.Seller;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 같은 셀러만 묶일 수 있음
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(name = "total_original_price", nullable = false)
    private Long totalOriginalPrice;

    @Column(name = "total_discounted_price", nullable = false)
    private Long totalDiscountedPrice;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseStatus status;

    public Purchase(Seller seller,
                    Long totalOriginalPrice,
                    Long totalDiscountedPrice) {
        this.seller = seller;
        this.totalOriginalPrice = totalOriginalPrice;
        this.totalDiscountedPrice = totalDiscountedPrice;
        this.createdAt = LocalDateTime.now();
        this.status = PurchaseStatus.NOT_PAID;
    }
}
