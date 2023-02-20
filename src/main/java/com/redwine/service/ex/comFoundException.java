package com.redwine.service.ex;

public class comFoundException extends RuntimeException{
    public comFoundException() {
        super();
    }

    public comFoundException(String message) {
        super(message);
    }

    public comFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public comFoundException(Throwable cause) {
        super(cause);
    }

    protected comFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
