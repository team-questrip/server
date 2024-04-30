package com.questrip.reward.support.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;

    public GlobalException(ErrorCode code) {
        this.errorCode = code;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        }

        return String.format("%s %s", errorCode.getMessage(), message);
    }
}