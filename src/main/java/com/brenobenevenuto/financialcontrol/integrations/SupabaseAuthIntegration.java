package com.brenobenevenuto.financialcontrol.integrations;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.brenobenevenuto.financialcontrol.domain.Request.AlterRoleRequest;
import com.brenobenevenuto.financialcontrol.domain.Request.AuthUsersRequest;
import com.brenobenevenuto.financialcontrol.domain.Request.LoginUsersRequest;
import com.brenobenevenuto.financialcontrol.exceptions.SupabaseApiException;

import static com.brenobenevenuto.financialcontrol.constants.Constants.SUPABASE_ERROR_STRING;
import static com.brenobenevenuto.financialcontrol.constants.Constants.GRANT_TYPE;
import static com.brenobenevenuto.financialcontrol.constants.Constants.PASSWORD;
import static com.brenobenevenuto.financialcontrol.constants.Constants.APP_METADATA;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupabaseAuthIntegration {
    
    @Qualifier("supabaseWebClient")
    private final WebClient supWebClient;

    public void signup(AuthUsersRequest request) {
        supWebClient.post()
            .uri("/auth/v1/admin/users")
            .bodyValue(request)
            .retrieve()
            .onStatus(
                HttpStatusCode::isError,
                response -> response.bodyToMono(String.class)
                    .map(body -> new SupabaseApiException(SUPABASE_ERROR_STRING + body))
            )
            .toBodilessEntity()
            .block();
    }

    public Map<String, Object> login(LoginUsersRequest request) {
        return supWebClient.post()
            .uri(uriBuilder -> uriBuilder
                .path("/auth/v1/token")
                .queryParam(GRANT_TYPE, PASSWORD)
                .build()
            )
            .bodyValue(request)
            .retrieve()
            .onStatus(
                HttpStatusCode::isError,
                response -> response.bodyToMono(String.class)
                    .map(body -> new SupabaseApiException(SUPABASE_ERROR_STRING + body))
            )
            .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
            .block();
    }

    public void alterRole(UUID userId, AlterRoleRequest reqSupabaseAlterRole) {
        Map<String, Object> user = getUser(userId);
        Map<String, Object> appMetadata =
            (Map<String, Object>) user.getOrDefault(APP_METADATA, new HashMap<>());

        appMetadata.put("role", reqSupabaseAlterRole.getRole().getName());

        supWebClient.put()
            .uri(uriBuilder -> uriBuilder
                .path("/auth/v1/admin/users/{userId}")
                .build(userId)
            )
            .bodyValue(Map.of(
                APP_METADATA, appMetadata
            ))
            .retrieve()
            .onStatus(
                HttpStatusCode::isError,
                response -> response.bodyToMono(String.class)
                    .map(body -> new SupabaseApiException(SUPABASE_ERROR_STRING + body))
            )
            .toBodilessEntity()
            .block();
    }

    private Map<String, Object> getUser(UUID userId){
        return supWebClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/auth/v1/admin/users/{userId}")
                .build(userId)
            )
            .retrieve()
            .onStatus(
                HttpStatusCode::isError,
                response -> response
                    .bodyToMono(String.class)
                    .map(body -> new SupabaseApiException(SUPABASE_ERROR_STRING + body))
            )
            .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
            .block();
    }
}
