package com.thomaz.library.exceptions;

public class DuplicateRegisterException extends RuntimeException {

    public DuplicateRegisterException(String message) {
        super(message);
    }
}
