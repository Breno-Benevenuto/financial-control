package com.brenobenevenuto.financialcontrol.ports;

import com.brenobenevenuto.financialcontrol.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPort {

    User save(User user);
}
