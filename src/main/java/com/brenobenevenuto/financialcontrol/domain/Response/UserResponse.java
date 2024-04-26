package com.brenobenevenuto.financialcontrol.domain.Response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;


public record UserResponse(long Id,
                           String Name,
                           String UserName) {
}
