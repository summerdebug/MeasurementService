package com.utilities.monitoring.exception;

import org.springframework.http.HttpStatus;

public class MeasurementServiceException extends RuntimeException {

    private static final long serialVersionUID = -6681694130831249833L;

    MeasurementServiceException(String msg) {
        super(msg);
    }

    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
