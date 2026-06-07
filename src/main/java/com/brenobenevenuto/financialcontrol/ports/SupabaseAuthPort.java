package com.brenobenevenuto.financialcontrol.ports;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.brenobenevenuto.financialcontrol.domain.Request.AlterRoleRequest;
import com.brenobenevenuto.financialcontrol.domain.Request.AuthUsersRequest;
import com.brenobenevenuto.financialcontrol.domain.Request.LoginUsersRequest;
import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;

@Component
public interface SupabaseAuthPort {
    void signup(AuthUsersRequest request);
    Map<String, Object> login(LoginUsersRequest request);
    UserResponse getUserInfo(String userId);
    void alterRole(UUID userId, AlterRoleRequest reqSupabaseAlterRole);
}
