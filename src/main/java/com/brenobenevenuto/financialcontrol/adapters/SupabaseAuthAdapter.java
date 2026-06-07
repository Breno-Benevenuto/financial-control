package com.brenobenevenuto.financialcontrol.adapters;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brenobenevenuto.financialcontrol.domain.Request.AlterRoleRequest;
import com.brenobenevenuto.financialcontrol.domain.Request.AuthUsersRequest;
import com.brenobenevenuto.financialcontrol.domain.Request.LoginUsersRequest;
import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;
import com.brenobenevenuto.financialcontrol.exceptions.UserNotFoundException;
import com.brenobenevenuto.financialcontrol.integrations.SupabaseAuthIntegration;
import com.brenobenevenuto.financialcontrol.ports.SupabaseAuthPort;
import com.brenobenevenuto.financialcontrol.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SupabaseAuthAdapter implements SupabaseAuthPort{

    private final SupabaseAuthIntegration integrations;

    private final UserRepository userRepository; 

    @Override
    public UserResponse getUserInfo(String userId) {
        log.info("[INFO] - [SUPABASE_ADAPTER] - finding user by id");
        var response = userRepository.findUserProfileById(UUID.fromString(userId))
            .orElseThrow(() -> new UserNotFoundException("User not Found"));
        log.info("[INFO] - [SUPABASE_ADAPTER] - user found by id");
        return response;
    }

    @Override
    public void signup(AuthUsersRequest request) {
        log.info("[INFO] - [SUPABASE_ADAPTER] - start signup email : {0}", request.getEmail());
        integrations.signup(request);
        log.info("[INFO] - [SUPABASE_ADAPTER] - end signup email : {0}", request.getEmail());
    }

    @Override
    public Map<String, Object> login(LoginUsersRequest request) {
        log.info("[INFO] - [SUPABASE_ADAPTER] - start login email {0}", request.getEmail());
        var response = integrations.login(request);
        log.info("[INFO] - [SUPABASE_ADAPTER] - end login email {0}", request.getEmail());
        return response;
    }

    @Override
    public void alterRole(UUID userId, AlterRoleRequest reqSupabaseAlterRole) {
        log.info("[INFO] - [SUPABASE_ADAPTER] - start alter role for {0}", reqSupabaseAlterRole.getRole().getName());
        integrations.alterRole(userId, reqSupabaseAlterRole);
        log.info("[INFO] - [SUPABASE_ADAPTER] - end alter role for {0}", reqSupabaseAlterRole.getRole().getName());
    }
}
