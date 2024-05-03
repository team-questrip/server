package com.questrip.reward.support.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_EXIST_PLACE(HttpStatus.BAD_REQUEST, "플레이스가 없습니다."),
    CAN_NOT_UPLOAD(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다."),
    EXTERNAL_SERVER_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "외부 API 서버 오류");

    private final HttpStatus status;
    private final String message;
}