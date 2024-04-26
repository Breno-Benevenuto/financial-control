package com.brenobenevenuto.financialcontrol.Controller;

import com.brenobenevenuto.financialcontrol.Service.UserService;
import com.brenobenevenuto.financialcontrol.domain.Request.UserRequest;
import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService _service;

    @PostMapping("/healthCheck")
    public boolean healthCheck(){
        return true;
    }


    @Operation(summary = "Cria um Usuario", responses = @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))))
    @PostMapping("/create")
    public UserResponse create(@Valid @RequestBody UserRequest user){
        return _service.CreateCommom(user);
    }

}
