package com.colpatria.bootcamp.exception;

public class UserException extends AbstractException {

    private final ApiResponseCode apiResponseCode;

    public UserException(String message) {
        super(message);
        this.apiResponseCode = ApiResponseCode.ERROR;
    }

    public UserException(String message, ApiResponseCode apiResponseCode) {
        super(message);
        this.apiResponseCode = apiResponseCode;
    }

    @Override
    public ApiResponseCode getApiResponseCode() {

        return apiResponseCode;
    }
}
