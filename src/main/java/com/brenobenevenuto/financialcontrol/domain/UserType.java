package com.brenobenevenuto.financialcontrol.domain;


import lombok.Getter;


public enum UserType {
    Admin(1),
    Commom(2);
    private int Value;
    private UserType(int value)
    {
        this.Value = value;
    }

    public int getValue() {
        return Value;
    }
}
