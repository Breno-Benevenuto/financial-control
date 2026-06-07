package com.brenobenevenuto.financialcontrol.domain.Request;

import lombok.Data;

@Data
public class AuthUsersRequest {
    private String email;
    private String password;
    private boolean email_confirm;
}
