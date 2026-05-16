package com.univille.api.shopplusai.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e){
        var responseError = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        var responseError = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
    "Erro de validação dos campos",
            errors.stream().map(ErrorValidationResponse::new).toList()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        var responseError = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Não há body na requisição", null);
        return ResponseEntity.badRequest().body(responseError);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        var resposeError = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
        return ResponseEntity.badRequest().body(resposeError);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        var responseError = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(responseError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e){
        var responseError = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
        return ResponseEntity.badRequest().body(responseError);
    }
}
