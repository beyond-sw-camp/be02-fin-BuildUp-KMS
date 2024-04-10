package com.example.bootshelf.user.controller.mock;

import com.example.bootshelf.user.model.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser annotation) {

        Integer idx = annotation.idx();
        String email = annotation.email();

        User user = User.builder()
                .idx(idx)
                .email(email)
                .name("test01")
                .authority("ROLE_USER")
                .build();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null,
                user.getAuthorities()
        );

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);

        return context;
    }
}
