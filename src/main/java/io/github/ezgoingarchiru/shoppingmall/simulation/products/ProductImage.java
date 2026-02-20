package io.github.ezgoingarchiru.shoppingmall.simulation.products;

import io.github.ezgoingarchiru.shoppingmall.simulation.images.Image;
import jakarta.persistence.*;

@Entity
@Table
public class ProductImage {
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected Product product;

    @ManyToOne
    protected Image image;

    protected Integer sortOrder;
}
