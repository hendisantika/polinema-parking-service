/*
 * aplikasi-akademik
 *
 * Copyright (c) 2019
 * All rights reserved
 * Written by od3ng created on 8/14/19, 1:09 PM
 * Blog    : sinaungoding.com
 * Email   : noprianto@sinaungoding.com,lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorResponse> customHandleNotFoundError(NotFoundException ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(new Date());
        errors.setError(HttpStatus.valueOf(NOT_FOUND.value()).getReasonPhrase());
        errors.setStatus(NOT_FOUND.value());
        errors.setMessage(ex.getMessage());
        errors.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errors, HttpStatus.valueOf(errors.getStatus()));

    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<CustomErrorResponse> customHandleInternalServerError(InternalServerErrorException ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(new Date());
        errors.setError(HttpStatus.valueOf(INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
        errors.setStatus(INTERNAL_SERVER_ERROR.value());
        errors.setMessage(ex.getMessage());
        errors.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errors, HttpStatus.valueOf(errors.getStatus()));

    }

    @ExceptionHandler(ClientException.class)
    public void springHandleApiError(HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED.value());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<CustomErrorResponse> customHandleAuthError(ApiException ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(new Date());
        errors.setError(HttpStatus.valueOf(ex.getStatus().getCode()).getReasonPhrase());
        errors.setStatus(ex.getStatus().getCode());
        errors.setMessage(ex.getStatus().getMessage());
        errors.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errors, HttpStatus.valueOf(errors.getStatus()));
    }

    // @Validate For Validating Path Variables and Request Parameters
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value());
    }

    // error handle for @Valid
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", dateFormat.format(new Date()));
        body.put("status", status.value());
        body.put("error", status.toString());

        //Get all fields errors
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        body.put("message", errors);
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(body, headers, status);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", dateFormat.format(new Date()));
        body.put("status", status.value());
        body.put("error", status.toString());
        body.put("message", ex.getMessage());
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(body, headers, status);
    }

}
