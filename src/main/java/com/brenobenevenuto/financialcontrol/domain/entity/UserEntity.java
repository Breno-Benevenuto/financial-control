package com.brenobenevenuto.financialcontrol.domain.entity;

import java.sql.Types;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    private String timezone;

    @Column(name = "currency", columnDefinition = "CHAR(3)")
    @JdbcTypeCode(Types.CHAR)
    private String currency;
}
