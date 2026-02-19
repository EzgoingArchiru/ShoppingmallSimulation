package com.chickenPizza.JavaShoppingWorld.products;

import com.chickenPizza.JavaShoppingWorld.images.Image;
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
