package com.brenobenevenuto.financialcontrol.Repositories;

import com.brenobenevenuto.financialcontrol.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
