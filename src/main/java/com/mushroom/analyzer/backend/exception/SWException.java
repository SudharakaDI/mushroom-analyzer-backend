package com.mushroom.analyzer.backend.exception;

import com.mushroom.analyzer.backend.exception.pojo.SWExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SWException extends Exception {

    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorType;
    private final String errorMessage;

    public SWException(HttpStatus statusCode,
                       SWExceptionCode errorCode,
                       String errorMessage) {
        this.statusCode = statusCode;
        this.errorCode = errorCode.name();
        this.errorType = errorCode.getType();
        this.errorMessage = errorMessage;
    }

    public SWException(HttpStatus statusCode,
                       SWExceptionCode errorCode,
                       String errorMessage,
                       String logMessage) {
        super(logMessage);
        this.statusCode = statusCode;
        this.errorCode = errorCode.name();
        this.errorType = errorCode.getType();
        this.errorMessage = errorMessage;
    }

    public SWException(HttpStatus statusCode,
                       SWExceptionCode errorCode,
                       String errorMessage,
                       String logMessage,
                       Throwable e) {
        super(logMessage, e);
        this.statusCode = statusCode;
        this.errorCode = errorCode.name();
        this.errorType = errorCode.getType();
        this.errorMessage = errorMessage;
    }

    public SWException(HttpStatus statusCode,
                       String errorCode,
                       String errorType,
                       String errorMessage,
                       String logMessage,
                       Throwable e) {
        super(logMessage, e);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}
