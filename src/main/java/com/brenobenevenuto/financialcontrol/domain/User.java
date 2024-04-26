package com.brenobenevenuto.financialcontrol.domain;
import com.brenobenevenuto.financialcontrol.domain.Request.UserRequest;
import jakarta.persistence.*;
import lombok.*;

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

    public String Name;

    @Column(unique = true)
    public String UserName;

    public String Password;

    public UserType Type;

    public User(String name, String userName, String passWord,UserType type)
    {
        this.Name = name;
        this.UserName = userName;
        this.Password = passWord;
        this.Type = type;
    }

    public static User map(UserRequest userRequest, UserType type)
    {
        return new User(userRequest.UserName(),userRequest.Name(),userRequest.Password(), type);
    }
}
