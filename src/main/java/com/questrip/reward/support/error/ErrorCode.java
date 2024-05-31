package com.questrip.reward.support.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_EXIST_PLACE(HttpStatus.BAD_REQUEST, "플레이스가 없습니다."),
    CAN_NOT_UPLOAD(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다."),
    EXTERNAL_SERVER_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "외부 API 서버 오류"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "유저를 찾을 수 없습니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 사용중인 이메일주소입니다."),


    // security
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 불일치합니다."),
    USERNAME_NOT_FOUND(HttpStatus.UNAUTHORIZED, "계정이 존재하지 않습니다."),
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "비밀번호가 불일치 합니다."),
    NOT_FOUND_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 없습니다."),
    NO_ACCESS(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "JWT 토큰이 만료되었습니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "지원하지 않는 토큰입니다.");

    private final HttpStatus status;
    private final String message;
}