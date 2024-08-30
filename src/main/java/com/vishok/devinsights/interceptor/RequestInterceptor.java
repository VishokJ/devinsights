package com.vishok.devinsights.interceptor;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestInterceptor implements Filter {

    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authToken = request.getHeader("Authorization").substring(7); // assuming the token is in the Authorization header

        String correlationId = jwtTokenUtil.getCorrelationIdFromToken(authToken);
        String userName = jwtTokenUtil.getUserNameFromToken(authToken);
        String userId = jwtTokenUtil.getUserIdFromToken(authToken);

        MDC.put("correlationId", correlationId);
        MDC.put("userName", userName);
        MDC.put("userId", userId);

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }
}
