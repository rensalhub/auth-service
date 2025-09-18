package com.bci.auth_service.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        String json = String.format("{\"mensaje\":\"%s\"}",
            "No tiene permisos para acceder a este recurso");
        response.getWriter().write(json);
    }

}
