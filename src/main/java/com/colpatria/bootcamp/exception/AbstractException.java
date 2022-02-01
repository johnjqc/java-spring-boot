package com.colpatria.bootcamp.exception;

public abstract class AbstractException extends RuntimeException {

    private static final long serialVersionUID = -2158099659916632057L;

    /**
     * Constructs a new runtime exception.
     */
    public AbstractException(String message) {

        super(message);
    }

    /**
     * Constructs a new runtime exception.
     *
     * @param message the message exception
     * @param cause the root cause
     */
    public AbstractException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Gets the associated {@link ApiResponseCode}.
     *
     * @return the associated {@link ApiResponseCode}.
     */
    public abstract ApiResponseCode getApiResponseCode();

}
