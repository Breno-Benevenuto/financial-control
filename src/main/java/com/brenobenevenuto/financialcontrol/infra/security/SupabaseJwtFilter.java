package com.brenobenevenuto.financialcontrol.infra.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Slf4j
@Component
public class SupabaseJwtFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder;


     @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String token = resolveToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            authenticate(token);
            logRequest(request);
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            SecurityContextHolder.clearContext();
            throw new BadCredentialsException("Token invalido ou expirado", e);
        }
    }
    private String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring(7).trim();
    }

    private void authenticate(String token) {
        Jwt jwt = jwtDecoder.decode(token);

        String userId = jwt.getSubject();
        String role = extractRole(jwt);

        var authentication = new UsernamePasswordAuthenticationToken(
            userId,
            null,
            List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String extractRole(Jwt jwt) {
        Map<String, Object> appMetadata = jwt.getClaimAsMap("app_metadata");

        if (appMetadata == null || !appMetadata.containsKey("role")) {
            return "AUTHENTICATED";
        }

        return appMetadata.get("role").toString().toUpperCase();
    }

    private void logRequest(HttpServletRequest request){
        log.info("[INFO] -[{0}] Request recebida : {1}", request.getMethod(), request.getRequestURI());
    }
}