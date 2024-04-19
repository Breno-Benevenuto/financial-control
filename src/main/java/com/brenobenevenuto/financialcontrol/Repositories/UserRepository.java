package com.brenobenevenuto.financialcontrol.Repositories;

import com.brenobenevenuto.financialcontrol.domain.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
}
