package com.gl.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Request logging filter to log all incoming HTTP requests and responses.
 * Helps with debugging and monitoring API usage.
 */
@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        long startTime = System.currentTimeMillis();
        
        // Log incoming request
        log.info(">>> Incoming Request: {} {} from {} | User-Agent: {}", 
            request.getMethod(), 
            request.getRequestURI(), 
            request.getRemoteAddr(),
            request.getHeader("User-Agent"));
        
        // Log request headers for debugging CORS
        log.debug("Request Headers: Origin={}, Referer={}", 
            request.getHeader("Origin"), 
            request.getHeader("Referer"));
        
        try {
            // Continue with the filter chain
            filterChain.doFilter(request, response);
        } finally {
            // Log response
            long duration = System.currentTimeMillis() - startTime;
            log.info("<<< Response: {} {} | Status={} | Duration={}ms", 
                request.getMethod(), 
                request.getRequestURI(), 
                response.getStatus(),
                duration);
            
            // Log CORS headers for debugging
            log.debug("Response CORS Headers: Access-Control-Allow-Origin={}, Access-Control-Allow-Credentials={}", 
                response.getHeader("Access-Control-Allow-Origin"),
                response.getHeader("Access-Control-Allow-Credentials"));
        }
    }
}
