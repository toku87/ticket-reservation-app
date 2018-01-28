package com.github.java4wro.user.model;



import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Entity
@Getter
@Setter

public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Email(message = "Please, provide a valid email")
    @NotEmpty
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String newPassword;

    private UserRole role;

    private boolean enabled;

    public Date getExpiryDate (){
        return new Date(getCreatedAt().getTime()+ TimeUnit.DAYS.toMillis(1));
    }

}
