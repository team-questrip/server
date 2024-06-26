package com.questrip.reward.api;

import com.questrip.reward.support.error.GlobalException;
import com.questrip.reward.support.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException(GlobalException e) {
        log.error("error occur {}", e.getMessage());

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ApiResponse.error(e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiResponse<Void>>> handleArgsValid(MethodArgumentNotValidException e) {
        log.error("error occur {}", e.getMessage());

        List<ApiResponse<Void>> errors = new ArrayList<>();
        e.getFieldErrors().stream()
                .forEach(error -> errors.add(ApiResponse.error(error.getDefaultMessage())));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}