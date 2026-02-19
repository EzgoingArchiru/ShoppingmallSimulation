package com.chickenPizza.JavaShoppingWorld.users;

public enum UserType {
    CUSTOMER,
    SELLER;
    public static UserType from(String str) {
        try {
            return UserType.valueOf(str.toLowerCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid UserType: " + str);
        }
    }
}