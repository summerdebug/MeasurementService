package com.utilities.monitoring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MeasurementServiceException.class)
    public ResponseEntity<String> handle(MeasurementServiceException e) {
        log.error("MeasurementServiceException was thrown. Unable to process the request.", e);
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        log.error("General Exception was thrown. Unable to process the request.", e);
        // In case of general exception we do not reveal the error message to the client.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error.");
    }
}
