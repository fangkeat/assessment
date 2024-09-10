package com.example.assessment.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Log request info
        logger.info("Incoming Request: Method: [{}], URI: [{}], Remote Address: [{}]",
                request.getMethod(), request.getRequestURI(), request.getRemoteAddr());

        // Log headers (optional)
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("Header: [{}: {}]", headerName, request.getHeader(headerName));
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Log response info
        logger.info("Outgoing Response: Status: [{}], Content-Type: [{}]",
                response.getStatus(), response.getContentType());
    }
}
