package com.brenobenevenuto.financialcontrol.Adapter;

import com.brenobenevenuto.financialcontrol.Port.UserPort;
import com.brenobenevenuto.financialcontrol.Repositories.UserRepository;
import com.brenobenevenuto.financialcontrol.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAdapter implements UserPort {

    @Autowired
    private final UserRepository _userRepository;

    @Override
    public User save(User user)  {
        return _userRepository.save(user);
    }

}
