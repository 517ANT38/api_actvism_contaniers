package com.charity.activism.models;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static org.hibernate.annotations.CascadeType.PERSIST;
import static org.hibernate.annotations.CascadeType.REFRESH;
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
    @JsonIgnore
    private List<UserActivismFond> userActivismFonds;

    @ManyToMany
    @JoinTable(
        name="roleuser",
        joinColumns = @JoinColumn(name = "idrole"),
        inverseJoinColumns = @JoinColumn(name = "iduser")
    )    
    @Cascade({PERSIST,REFRESH})
    private Set<Role> roles;

}
