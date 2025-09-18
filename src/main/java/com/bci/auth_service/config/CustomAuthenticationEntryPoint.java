package com.bci.auth_service.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        String json = String.format("{\"mensaje\":\"%s\"}",
            "No se encontró el token JWT en la cabecera Authorization o es inválido");
        response.getWriter().write(json);
    }

}
