package com.brenobenevenuto.financialcontrol.Port;

import com.brenobenevenuto.financialcontrol.domain.User;
import com.brenobenevenuto.financialcontrol.domain.UserType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPort {

    User save(User user);
    void delete(long id);
    Optional<User> getById(long id);
}
