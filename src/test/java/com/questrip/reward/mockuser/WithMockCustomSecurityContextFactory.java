package com.questrip.reward.mockuser;

import com.questrip.reward.domain.user.User;
import com.questrip.reward.fixture.UserFixture;
import com.questrip.reward.security.details.LoginUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.List;

public class WithMockCustomSecurityContextFactory implements WithSecurityContextFactory<MockUser> {
    @Override
    public SecurityContext createSecurityContext(MockUser annotation) {
        User user = UserFixture.get(1L);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_" + user.getRole());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(new LoginUser(user), "password", authorities);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);

        return context;
    }
}