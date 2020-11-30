package com.utilities.monitoring.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2731744292169148059L;

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
