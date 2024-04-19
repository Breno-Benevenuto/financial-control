package com.brenobenevenuto.financialcontrol.Service;


import com.brenobenevenuto.financialcontrol.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User Create(String name, String passWord, String userName){
        return new User(name, passWord, userName);
    }
}
