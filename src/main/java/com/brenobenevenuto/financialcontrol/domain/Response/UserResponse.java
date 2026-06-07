package com.brenobenevenuto.financialcontrol.domain.Response;

import java.math.BigDecimal;
import java.util.UUID;

public record UserResponse(
    UUID userId,
    String fullName,
    String avatarUrl,
    String timezone,
    String currency,
    Boolean darkMode,
    Boolean receiveEmailNotifications,
    BigDecimal monthlyBudget) {}
