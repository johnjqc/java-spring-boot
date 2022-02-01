package com.colpatria.bootcamp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiResponseCode {

    SUCCESS("SCS-00", "Success", HttpStatus.OK),

    ERROR("ERR-01", "Error on business layer", HttpStatus.INTERNAL_SERVER_ERROR),

    INVALID_DATA("ERR-02", "Invalid request data", HttpStatus.BAD_REQUEST),

    METHOD_NOT_ALLOWED("ERR-03", "Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED),

    USER_NOT_FOUND("ERR-04", "User Not Found", HttpStatus.NOT_FOUND);

    private final String code;

    private final String description;

    private final HttpStatus httpStatus;

}