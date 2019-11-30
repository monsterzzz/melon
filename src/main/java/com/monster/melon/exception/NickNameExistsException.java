package com.monster.melon.exception;

public class NickNameExistsException extends Exception {
    public NickNameExistsException() {
    }

    public NickNameExistsException(String message) {
        super(message);
    }

    public NickNameExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NickNameExistsException(Throwable cause) {
        super(cause);
    }

    public NickNameExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
