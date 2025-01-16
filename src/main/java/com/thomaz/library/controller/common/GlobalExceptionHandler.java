package com.thomaz.library.controller.common;

import com.thomaz.library.exceptions.DuplicateRegisterException;
import com.thomaz.library.exceptions.NotAllowedOperation;
import com.thomaz.library.model.dto.ErrorField;
import com.thomaz.library.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<ErrorField> list = fieldErrors.stream().map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage())).toList();
        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Error in validation", list);
    }

    @ExceptionHandler(DuplicateRegisterException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateRegisterException(DuplicateRegisterException ex) {
        return ErrorResponse.conflict(ex.getMessage());
    }

    @ExceptionHandler(NotAllowedOperation.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotAllowedOperationException(NotAllowedOperation ex) {
        return ErrorResponse.defaultError(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(RuntimeException ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro inesperado (•̀ - •́ )", List.of());
    }
}
