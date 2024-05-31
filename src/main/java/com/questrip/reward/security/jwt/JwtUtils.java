package com.questrip.reward.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.questrip.reward.domain.user.User;
import com.questrip.reward.security.details.LoginUser;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.response.ApiResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import static com.questrip.reward.security.jwt.JwtProperties.*;
import static com.questrip.reward.support.error.ErrorCode.*;

@Slf4j
@Component
public class JwtUtils {

    public static String key;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${jwt.app.jwtSecretKey}")
    private void setKey(String key) {
        this.key = key;
    }

    public static Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String generateAccessToken(User user) {
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("username",user.getUsername())
                .claim("role", User.Role.USER)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static String parseJwtToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
        }
        return token;
    }

    public static boolean validationJwtToken(String token, HttpServletResponse response) throws IOException {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            setResponse(response, INVALID_JWT_TOKEN);
        } catch (ExpiredJwtException e) {
            setResponse(response, EXPIRED_ACCESS_TOKEN);
        } catch (UnsupportedJwtException e) {
            setResponse(response, UNSUPPORTED_JWT_TOKEN);
        }
        return false;
    }

    private static void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        log.error("error message {}", errorCode.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiResponse<Void> fail = ApiResponse.error(errorCode.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(fail));
    }

    public static LoginUser verify(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        Long id = claims.get("id", Long.class);
        String email = claims.get("email", String.class);
        String username = claims.get("username", String.class);
        String role = claims.get("role", String.class);

        User user = User.builder()
                .id(id)
                .email(email)
                .username(username)
                .role(User.Role.valueOf(role))
                .build();

        return new LoginUser(user);
    }

}