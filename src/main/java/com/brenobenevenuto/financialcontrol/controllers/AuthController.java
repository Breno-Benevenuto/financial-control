package com.brenobenevenuto.financialcontrol.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brenobenevenuto.financialcontrol.domain.Request.AlterRoleRequest;
import com.brenobenevenuto.financialcontrol.domain.Request.LoginUsersRequest;
import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;
import com.brenobenevenuto.financialcontrol.domain.enums.Role;
import com.brenobenevenuto.financialcontrol.domain.services.SupabaseAuthService;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {
    private final SupabaseAuthService authService;
    
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody LoginUsersRequest request){
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUsersRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('AUTHENTICATED') and isAuthenticated()")
    public ResponseEntity<UserResponse> me(Authentication authentication) {
        return ResponseEntity.ok(authService.getMe(authentication));
    }

    @PatchMapping("/role/{userId}")
    @PreAuthorize("hasRole('ADMIN') and isAuthenticated()")
    public ResponseEntity<Void> role(@PathVariable("userId") UUID userId, @RequestBody AlterRoleRequest request){
        authService.alterRole(userId, request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('AUTHENTICATED') and isAuthenticated()")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(authService.getRole());
    }
}
