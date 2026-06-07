package com.brenobenevenuto.financialcontrol.domain.enums;


import org.springframework.http.HttpStatus;

import com.brenobenevenuto.financialcontrol.exceptions.SupabaseApiException;
import com.brenobenevenuto.financialcontrol.exceptions.UserNotFoundException;

public enum ExceptionCodeMappingEnum {
    USER_NOTFOUND_EXCEPTION(UserNotFoundException.class, HttpStatus.NOT_FOUND.value()),
    SUPABASE_API_EXCEPTION(SupabaseApiException.class, HttpStatus.BAD_REQUEST.value());


    private Class<? extends RuntimeException> exClass;
    private int httpCode;

    ExceptionCodeMappingEnum(Class<? extends RuntimeException> exClass, int httpCode){
        this.exClass = exClass;
        this.httpCode = httpCode;
    }

    public static int getHttpCodeForEx(RuntimeException ex){
        for(var mapping : values()){
            if (mapping.exClass.isInstance(ex)) {
                return mapping.httpCode;
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
