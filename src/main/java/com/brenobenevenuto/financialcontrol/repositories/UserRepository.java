package com.brenobenevenuto.financialcontrol.repositories;

import com.brenobenevenuto.financialcontrol.domain.Response.UserResponse;
import com.brenobenevenuto.financialcontrol.domain.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{

    @Query("""
        SELECT new com.brenobenevenuto.financialcontrol.domain.Response.UserResponse(
            u.id,
            u.fullName,
            u.avatarUrl,
            u.timezone,
            u.currency,
            us.darkMode,
            us.receiveEmailNotifications,
            us.monthlyBudget
        )
        FROM UserEntity u
        LEFT JOIN UserSettingsEntity us
            ON us.userId = u.id
        WHERE u.id = :userId
    """)
    Optional<UserResponse> findUserProfileById(UUID userId);
}
