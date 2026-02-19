package com.chickenPizza.JavaShoppingWorld.purchase;

public enum PurchaseStatus {

    NOT_PAID,        // 결제 전
    PAID,            // 결제 완료
    CANCELED,        // 주문 취소
    PARTIAL_REFUND,  // 부분 환불
    REFUNDED;         // 전체 환불
    public static PurchaseStatus from(String str) {
        try {
            return PurchaseStatus.valueOf(str.toLowerCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid PurchaseStatus: " + str);
        }
    }
}
