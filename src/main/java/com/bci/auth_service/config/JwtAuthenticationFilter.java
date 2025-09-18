package com.bci.auth_service.config;

import com.bci.auth_service.commons.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        if (token != null) {
            try {
                Claims claims = jwtUtil.parseToken(token);
                String userId = claims.getSubject();
                if (userId != null) {
                    UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(
                        userId, null, Collections.emptyList());
                    authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                try {
                    String json = String.format("{\"mensaje\":\"%s\"}",
                        "Token JWT inválido o expirado");
                    response.getWriter().write(json);
                } catch (Exception ex) {
                    response.getWriter().write("{\"mensaje\":\"Token JWT inválido o expirado\"}");
                }
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
