package com.brenobenevenuto.financialcontrol.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig{
    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-role-key}")
    private String serviceRoleKey;
    

    @Bean(name = "supabaseWebClient")
    public WebClient supabaseWebClient(){
        return WebClient.builder()
                .baseUrl(supabaseUrl)
                .defaultHeader("apikey", serviceRoleKey)
                .defaultHeader("Authorization", "Bearer " + serviceRoleKey)
                .build();
    }
}

