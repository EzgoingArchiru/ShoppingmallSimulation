package io.github.ezgoingarchiru.shoppingmall.simulation.exceptions;


import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final String resource;
    private final String reason;
    public EntityNotFoundException(String resource, String reason) {
        super(String.format("%s not found, reason: %s", resource, reason));
        this.resource = resource;
        this.reason = reason;
    }
}
