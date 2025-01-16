package com.thomaz.library.model.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(int status, String message, List<ErrorField> erros) {

    public static ErrorResponse defaultError(String message) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, null);
    }


    public static ErrorResponse conflict(String message) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, null);
    }


}
