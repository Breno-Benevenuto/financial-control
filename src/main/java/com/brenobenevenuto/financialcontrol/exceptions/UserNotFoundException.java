package com.brenobenevenuto.financialcontrol.exceptions;

public class UserNotFoundException extends RuntimeException { 
    UserNotFoundException(String message){super(message);}
}
