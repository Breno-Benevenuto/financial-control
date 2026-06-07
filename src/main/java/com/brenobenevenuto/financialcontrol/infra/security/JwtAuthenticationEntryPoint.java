package com.brenobenevenuto.financialcontrol.infra.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.brenobenevenuto.financialcontrol.domain.Response.ApiExceptionResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        var apiExceptionResponse = new ApiExceptionResponse(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado ou inválido", Collections.EMPTY_LIST);
        response.getWriter().write(apiExceptionResponse.writeApiExceptionResponseAsJson());
    }
    
}
