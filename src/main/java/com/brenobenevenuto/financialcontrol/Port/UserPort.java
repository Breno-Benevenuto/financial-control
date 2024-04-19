package com.brenobenevenuto.financialcontrol.Port;

import com.brenobenevenuto.financialcontrol.domain.User;
import com.brenobenevenuto.financialcontrol.domain.UserType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPort {

    User save(User user);
}
