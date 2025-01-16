package com.thomaz.library.exceptions;

public class InvalidFieldException extends RuntimeException {

    private String field;

    public InvalidFieldException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
