package com.machineric.machinericpractice.exception.handler;

import com.machineric.machinericpractice.exception.DataNotFoundException;
import com.machineric.machinericpractice.exception.FeignClientException;
import com.machineric.machinericpractice.exception.response.ErrorResponse;
import com.machineric.machinericpractice.exception.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FeignClientException.class)
    public ResponseEntity<ErrorResponse> handleFeignClientException(FeignClientException ex) {
        logger.error("Resource: " + ex.getResource() + " - " + "Message: " + ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse();
        error.setTitle(ex.getTitle());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex) {
        logger.error(ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse();
        error.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown(Exception ex) {
        logger.error(ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse();
        error.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setMessage(ResponseMessage.ERROR_INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
