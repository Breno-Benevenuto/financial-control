package com.brenobenevenuto.financialcontrol.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.GUIDGenerator;
import org.springframework.data.repository.NoRepositoryBean;

import java.security.PublicKey;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "Id")
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;

    @Column(unique = true)
    public String Name;
    @Column(unique = true)
    public String UserName;
    @Column(unique = true)

    public String Password;

    @Enumerated(EnumType.STRING)
    public UserType Type;

    public User(String name, String passWord, String userName)
    {
        Name = name;
        Password = passWord;
        UserName = userName;
    }

}
