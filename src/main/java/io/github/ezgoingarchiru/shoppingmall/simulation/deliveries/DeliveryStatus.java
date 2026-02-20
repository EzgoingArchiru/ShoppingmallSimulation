package io.github.ezgoingarchiru.shoppingmall.simulation.deliveries;

public enum DeliveryStatus {
    PENDING, // 준비중
    HANDED_OVER_TO_CARRIER, // 배달 회사로 가능중
    IN_TRANSIT, // 운송중
    DELIVERED // 도착
}
