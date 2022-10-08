package org.winther.backend.wintherburguer.exception;

public class CustomNoContentException extends RuntimeException {

    public CustomNoContentException(String message) {
        super(message);
    }

    public CustomNoContentException(String message, Throwable cause) {
        super(message, cause);
    }
 
}
