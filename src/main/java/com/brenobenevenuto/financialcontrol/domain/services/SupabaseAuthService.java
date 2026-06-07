package com.brenobenevenuto.financialcontrol.domain.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.brenobenevenuto.financialcontrol.converter.AuthUsersRequestConverter;
import com.brenobenevenuto.financialcontrol.domain.Request.AlterRoleRequest;
import com.brenobenevenuto.financialcontrol.domain.Request.LoginUsersRequest;
import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;
import com.brenobenevenuto.financialcontrol.domain.enums.Role;
import com.brenobenevenuto.financialcontrol.ports.SupabaseAuthPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupabaseAuthService {
    private final SupabaseAuthPort port;

    public void signup(LoginUsersRequest request){
        port.signup(AuthUsersRequestConverter.convert(request));
    }

    public Map<String, Object> login(LoginUsersRequest request){
        return port.login(request);
    }

    public UserResponse getMe(Authentication authentication){
        String userId = authentication.getPrincipal().toString();
        return port.getUserInfo(userId);
    }

    public void alterRole(UUID userId, AlterRoleRequest request){
        port.alterRole(userId, request);
    }

    public List<Role> getRole(){
        return Role.getAllRole();
    }
}
