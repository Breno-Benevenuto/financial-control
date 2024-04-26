package com.brenobenevenuto.financialcontrol.controllers;

import com.brenobenevenuto.financialcontrol.domain.Request.UserRequest;
import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;
import com.brenobenevenuto.financialcontrol.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Operation(summary = "Retorna um Usuario por Id", responses = @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))))
    @PostMapping("/getById")
    public UserResponse getById(@Valid @RequestParam long id){
        return _service.GetById(id);
    }


    @Operation(summary = "", responses = @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))))
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> create(@RequestParam long id){
        //TODO: Criar sumario, e tratamento de ex.
        var result = _service.DeleteById(id);

        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

}
