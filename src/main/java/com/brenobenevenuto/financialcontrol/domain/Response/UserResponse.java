package com.brenobenevenuto.financialcontrol.domain.Response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserResponse(String Name,
                           String UserName) {
}
