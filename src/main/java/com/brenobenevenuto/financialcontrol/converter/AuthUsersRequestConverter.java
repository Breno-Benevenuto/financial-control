package com.brenobenevenuto.financialcontrol.converter;

import com.brenobenevenuto.financialcontrol.domain.Request.AuthUsersRequest;
import com.brenobenevenuto.financialcontrol.domain.Request.LoginUsersRequest;

public class AuthUsersRequestConverter {
    
    public static AuthUsersRequest convert(LoginUsersRequest request){
        var response = new AuthUsersRequest();
        response.setEmail(request.getEmail());
        response.setPassword(request.getPassword());
        response.setEmail_confirm(true);
        return response;
    }
}
