package com.brenobenevenuto.financialcontrol.services;


import com.brenobenevenuto.financialcontrol.domain.Request.UserRequest;
import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;
import com.brenobenevenuto.financialcontrol.exceptions.UserNotFoundException;
import com.brenobenevenuto.financialcontrol.ports.UserPort;
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

        return new UserResponse(newCommomUser.getId(), newCommomUser.getName(), newCommomUser.getUserName());
    }

    public UserResponse GetById(long id)
    {
        User user = _userPort.getById(id).orElseThrow(() -> new UserNotFoundException("Usuário Não Encontrado"));
        return new UserResponse(user.getId(), user.getName(), user.getUserName());
    }

    public Boolean DeleteById(long id)
    {
        if (_userPort.getById(id).isEmpty()){
            return false; //TODO: Exeption
        }
        _userPort.delete(id);
        return true;
    }

}
