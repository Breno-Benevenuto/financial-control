package com.brenobenevenuto.financialcontrol.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    @RequestMapping("/healthCheck")
    public boolean healthCheck(){
        return true;
    }


}
