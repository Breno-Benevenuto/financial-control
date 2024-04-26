package com.brenobenevenuto.financialcontrol.Adapter;

import com.brenobenevenuto.financialcontrol.Port.UserPort;
import com.brenobenevenuto.financialcontrol.Repositories.UserRepository;
import com.brenobenevenuto.financialcontrol.domain.User;
import com.brenobenevenuto.financialcontrol.domain.UserType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAdapter implements UserPort {

    @Autowired
    private final UserRepository _userRepository;

    @Override
    public User save(User user)  {
        return _userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        _userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getById(long id) {
        return _userRepository.findById(id);
    }

}
