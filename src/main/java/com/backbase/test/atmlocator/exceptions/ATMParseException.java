package com.backbase.test.atmlocator.exceptions;

/**
 * Created by luizsantana on 10/6/15.
 */
public class ATMParseException extends Exception {
    public ATMParseException() {
    }

    public ATMParseException(String message) {
        super(message);
    }

    public ATMParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ATMParseException(Throwable cause) {
        super(cause);
    }

    public ATMParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
