package io.github.ezgoingarchiru.shoppingmall.simulation.payments;

public enum PaymentStatus {
    PAY_PENDING,
    CANCELED,
    PAID,
    FAILED;
    public static PaymentStatus from(String str) {
        try {
            return PaymentStatus.valueOf(str.toLowerCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ProductStatus: " + str);
        }
    }
}
