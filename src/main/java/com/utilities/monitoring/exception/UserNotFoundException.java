package com.utilities.monitoring.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends MeasurementServiceException {

    private static final long serialVersionUID = -2731744292169148059L;

    public UserNotFoundException(Long userId) {
        super("User with id " + userId + " not found.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
