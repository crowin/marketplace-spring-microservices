package org.github.crowin.marketservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.github.crowin.marketservice.dto.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice @Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientBasicException.class)
    public ResponseEntity<BasicResponse<Void>> handleClientBasicException(ClientBasicException e) {
        log.error("ClientBasicException log: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BasicResponse.error(e.getMessage(), e.getErrorCode().name()));
    }
}
