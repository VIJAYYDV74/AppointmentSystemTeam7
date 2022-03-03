package com.team7.appointmentsystem.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        response.getWriter().append("Authentication Success");
        response.setStatus(200);
        authorities.forEach(authority -> {
            if(authority.getAuthority().equals("ROLE_USER")) {
                try {
                    System.out.println("Login Successful...Redirecting");
                    redirectStrategy.sendRedirect(request, response, "/login-success");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(authority.getAuthority().equals("ROLE_ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/admin");
                } catch (Exception e) {

                    e.printStackTrace();
                }
            } else {
                throw new IllegalStateException();
            }
        });

    }
}
