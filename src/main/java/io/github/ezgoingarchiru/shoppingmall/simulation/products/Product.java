package io.github.ezgoingarchiru.shoppingmall.simulation.products;

import io.github.ezgoingarchiru.shoppingmall.simulation.users.Seller;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected Seller seller;

    protected String name;

    protected Long originalPrice;

    protected Long discountedPrice;

    protected Long version;
    protected Long stock;
    @ManyToOne
    protected ProductCategory category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected ProductStatus status;

    @CreationTimestamp
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    protected LocalDateTime updatedAt;
}


