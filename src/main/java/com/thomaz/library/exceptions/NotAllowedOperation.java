package com.thomaz.library.exceptions;

public class NotAllowedOperation extends RuntimeException {

    public NotAllowedOperation(String message) {
        super(message);
    }
}
