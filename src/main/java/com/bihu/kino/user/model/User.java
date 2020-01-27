package com.bihu.kino.user.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name="users")
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    String username;
    String password;
    @ManyToMany
    List<UserRole> roles;

    public User(long id, String username, String password, List<UserRole> roles) {
        this.id = id;
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.roles = roles;
    }

    public void setPassword(String password){
       this.password = new BCryptPasswordEncoder().encode(password);
    }
}
