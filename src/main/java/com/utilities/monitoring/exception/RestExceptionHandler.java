package com.utilities.monitoring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.AbstractMap;

@ControllerAdvice
public class RestExceptionHandler {

    // TODO Configure writing logs to files
    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handle(Exception exception) {
        LOG.error("Exception: Unable to process this request. ", exception);
        final AbstractMap.SimpleEntry<String, String> response =
                new AbstractMap.SimpleEntry<>("Error message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
