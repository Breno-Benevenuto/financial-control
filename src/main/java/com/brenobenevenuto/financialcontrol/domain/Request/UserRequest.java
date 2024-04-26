package com.brenobenevenuto.financialcontrol.domain.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(
        String Name,
        String UserName,
        @NotEmpty(message = "A senha não pode ser vazia!")
        @Size(min = 8, message = "A senha deve ter no minimo 8 caracteres!")
        @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z]).*", message = "A senha deve conter pelo menos uma letra maiúscula e uma letra minúscula") String Password)
{
}
