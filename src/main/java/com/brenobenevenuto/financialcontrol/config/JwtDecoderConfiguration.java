package com.brenobenevenuto.financialcontrol.config;


import java.nio.charset.StandardCharsets;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtDecoderConfiguration {
    
    @Value("${supabase.jwtSecret}")
    private String jwtSecret;

    @Bean
    public JwtDecoder jwtDecoder(){
        var secretkey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        return NimbusJwtDecoder.withSecretKey(secretkey).build();
    }
}
