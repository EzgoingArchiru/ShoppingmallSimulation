package com.chickenPizza.JavaShoppingWorld.products;

import com.chickenPizza.JavaShoppingWorld.users.UserType;

public enum ProductStatus {
    ACTIVE,
    INACTIVE,
    SUSPENDED,
    OUT_OF_STOCK;
    public static ProductStatus from(String str) {
        try {
            return ProductStatus.valueOf(str.toLowerCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ProductStatus: " + str);
        }
    }
}
