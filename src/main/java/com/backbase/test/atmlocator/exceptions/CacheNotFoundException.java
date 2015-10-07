package com.backbase.test.atmlocator.exceptions;

/**
 * Created by luizsantana on 10/6/15.
 */
public class CacheNotFoundException extends Exception {
    public CacheNotFoundException() {
    }

    public CacheNotFoundException(String message) {
        super(message);
    }

    public CacheNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheNotFoundException(Throwable cause) {
        super(cause);
    }

    public CacheNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
