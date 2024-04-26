package com.brenobenevenuto.financialcontrol.Service;


import com.brenobenevenuto.financialcontrol.Port.UserPort;
import com.brenobenevenuto.financialcontrol.domain.Request.UserRequest;
import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;
import com.brenobenevenuto.financialcontrol.domain.User;
import com.brenobenevenuto.financialcontrol.domain.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    @Autowired
    private final UserPort _userPort;

    public UserResponse CreateCommom(UserRequest user){
        User newCommomUser = _userPort.save(User.map(user, UserType.Commom));

        return new UserResponse(newCommomUser.getName(), newCommomUser.getUserName());
    }
}
