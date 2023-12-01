package com.charity.activism.models;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class ActivismUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lastName;

    private String firstName;

    private String middleName;

    @ManyToOne
    @JoinColumn(name = "subdivisionId", referencedColumnName = "id")
    @Cascade(CascadeType.PERSIST)
    private Subdivision subdivision;

    private String login;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserActivismFond> userActivismFonds;

    @ManyToOne
    @JoinColumn(name = "rolesId", referencedColumnName = "id")
    @Cascade(CascadeType.PERSIST)
    private Role role;

}
