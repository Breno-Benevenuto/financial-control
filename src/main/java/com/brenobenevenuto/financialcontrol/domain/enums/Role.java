package com.brenobenevenuto.financialcontrol.domain.enums;

import java.util.List;

import lombok.Getter;

@Getter
public enum Role {
    AUTHENTICATED("AUTHENTICATED"),
    ADMIN("ADMIN");

    private String name;

    Role(String name){
        this.name = name;
    }

    public static List<Role> getAllRole(){
        return List.of(values());
    }
}
