package com.questrip.reward.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.questrip.reward.support.error.ErrorCode.*;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException.getClass().equals(UsernameNotFoundException.class)) {
            setResponse(response, USERNAME_NOT_FOUND);
        } else if (authException.getClass().equals(BadCredentialsException.class)) {
            setResponse(response, BAD_CREDENTIALS);
        } else if(authException.getClass().equals(InsufficientAuthenticationException.class)) {
            setResponse(response, INVALID_JWT_TOKEN);
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiResponse<Void> fail = ApiResponse.error(errorCode.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(fail));
    }
}