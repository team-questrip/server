package com.questrip.reward.security.filter;

import com.questrip.reward.security.details.LoginUser;
import com.questrip.reward.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final List<String> API_WHITE_LIST;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = JwtUtils.parseJwtToken(request);

        if(isPermitted(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (token != null && JwtUtils.validationJwtToken(token, response)) {
            LoginUser loginUser = JwtUtils.verify(token);
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPermitted(String requestUri) {
        for (String pattern : API_WHITE_LIST) {
            if (Pattern.matches(pattern, requestUri)) {
                return true;
            }
        }
        return false;
    }
}