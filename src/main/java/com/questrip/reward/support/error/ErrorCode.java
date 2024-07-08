package com.questrip.reward.support.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_EXIST_PLACE(HttpStatus.BAD_REQUEST, "Place does not exist."),
    CAN_NOT_UPLOAD(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image."),
    EXTERNAL_SERVER_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "External API server error"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "User not found."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "Email address is already in use."),
    NOT_FOUND_MENU_GROUP(HttpStatus.BAD_REQUEST, "Menu group does not exist."),
    CONTENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "Content not found."),
    PROGRESS_RECOMMEND_NOT_FOUND(HttpStatus.BAD_REQUEST, "Progress recommendation not found"),
    DISTANCE_CHECK_FAILED(HttpStatus.BAD_REQUEST, "Distance check failed"),
    ALREADY_EXIST_PROGRESS_RECOMMEND(HttpStatus.BAD_REQUEST, "Recommendation in progress already exists."),
    CAN_NOT_UPDATE_STATUS(HttpStatus.BAD_REQUEST, "Recommend status is not ACCEPTED"),
    UN_SUPPORTED_BLOCK_TYPE(HttpStatus.BAD_REQUEST, "Unsupported block type"),
    UN_SUPPORTED_LANGUAGE(HttpStatus.BAD_REQUEST, "Unsupported language"),

    // security
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Incorrect password."),
    USERNAME_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Account not found."),
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Incorrect password."),
    NOT_FOUND_TOKEN(HttpStatus.UNAUTHORIZED, "Token not found."),
    NO_ACCESS(HttpStatus.FORBIDDEN, "Access denied."),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid JWT token."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "JWT token has expired."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "Unsupported token.");

    private final HttpStatus status;
    private final String message;
}