package io.github.ezgoingarchiru.shoppingmall.simulation.payments;


public enum PaymentMethod {
    POINT;
    public static PaymentMethod from(String str) {
        try {
            return PaymentMethod.valueOf(str.toLowerCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ProductStatus: " + str);
        }
    }
}
