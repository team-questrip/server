package com.questrip.reward.support.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EXAMPLE(HttpStatus.BAD_REQUEST, "example");

    private final HttpStatus status;
    private final String message;
}