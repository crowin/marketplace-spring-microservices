package org.github.crowin.userservice.exception;

import org.github.crowin.userservice.util.ClientErrorCode;

public class ClientBasicException extends RuntimeException {
    private final ClientErrorCode errorCode;

    public ClientBasicException(String message, ClientErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
