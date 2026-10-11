package com.realm.auth_service.errors.exceptions;

public class RevokedTokenUseException extends RuntimeException {
    public RevokedTokenUseException(String message) {
        super(message);
    }

    public RevokedTokenUseException(String message, Throwable cause) {
        super(message, cause);
    }
}
