package com.colpatria.bootcamp.controller.advice;

import com.colpatria.bootcamp.exception.AbstractException;
import com.colpatria.bootcamp.exception.ApiResponseCode;
import com.colpatria.digitalfactory.api.dto.response.ApiResponseDF;
import com.colpatria.digitalfactory.api.dto.response.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ApiResponseDF<Void>> handleAbstractException(
            AbstractException ex) {

        ApiResponseCode responseCode = ex.getApiResponseCode();
        Notification notification = Notification.builder(
                responseCode.getDescription(),
                responseCode.getCode()
        ).build();

        log.error(String.format("Error response detail: [HttpCode: %s] [Code: %s] [Description: %s] [Message: %s]",
                responseCode.getHttpStatus().value() ,notification.getCode(), notification.getDescription(), ex.getMessage()),
                ex);

        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(new ApiResponseDF<>(notification));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseDF<Void>> handleAbstractException(
        ConstraintViolationException ex) {

        ApiResponseCode responseCode = ApiResponseCode.INVALID_DATA;
        Notification notification = Notification.builder(
                responseCode.getDescription(),
                responseCode.getCode()
        ).build();

        log.error(String.format("Error response detail: [HttpCode: %s] [Code: %s] [Description: %s] [Message: %s]",
                responseCode.getHttpStatus().value() ,notification.getCode(), notification.getDescription(), ex.getMessage()),
                ex);

        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(new ApiResponseDF<>(notification));
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponseDF<Void>> handleAbstractException(
            HttpRequestMethodNotSupportedException ex) {

        ApiResponseCode responseCode = ApiResponseCode.METHOD_NOT_ALLOWED;
        Notification notification = Notification.builder(
                responseCode.getDescription(),
                responseCode.getCode()
        ).build();

        log.error(String.format("Error request detail: [HttpCode: %s] [Code: %s] [Description: %s] [Message: %s]",
                        responseCode.getHttpStatus().value() ,notification.getCode(), notification.getDescription(), ex.getMessage()),
                ex);

        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(new ApiResponseDF<>(notification));
    }
}
