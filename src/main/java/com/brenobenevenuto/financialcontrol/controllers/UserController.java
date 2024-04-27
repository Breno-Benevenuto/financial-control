package com.brenobenevenuto.financialcontrol.controllers;

import com.brenobenevenuto.financialcontrol.domain.Request.UserRequest;
import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;
import com.brenobenevenuto.financialcontrol.domain.User;
import com.brenobenevenuto.financialcontrol.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService _service;

    @Operation(summary = "Cria um Usuario", responses = @ApiResponse(responseCode = "201", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))))
    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody UserRequest user){
        var result = _service.CreateCommom(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(String.format("{\"Id\": \"%s\"}", result.Id()));
    }


    @Operation(summary = "Retorna um Usuario por Id", responses = @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))))
    @PostMapping("/getById/{id}")
    public ResponseEntity<UserResponse> getById(@Valid @PathVariable long id){
       var result = _service.GetById(id);

       return ResponseEntity.status(HttpStatus.OK)
               .contentType(MediaType.APPLICATION_JSON)
               .body(result);
    }


    @Operation(summary = "", responses = @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        //TODO: Criar sumario.
        var result = _service.DeleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\": \"Usuário deletado com sucesso\"}");
    }

}
