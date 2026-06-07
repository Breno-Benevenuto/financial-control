package com.brenobenevenuto.financialcontrol.domain.entity;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_settings")
public class UserSettingsEntity {

    @Id
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "receive_email_notifications")
    private Boolean receiveEmailNotifications;

    @Column(name = "dark_mode")
    private Boolean darkMode;

    @Column(name = "monthly_budget")
    private BigDecimal monthlyBudget;
}
