package com.colpatria.bootcamp.exception;

public class MessageException extends AbstractException {

    public MessageException(String message) {
        super(message);
    }

    @Override
    public ApiResponseCode getApiResponseCode() {
        return ApiResponseCode.INVALID_DATA;
    }
}
