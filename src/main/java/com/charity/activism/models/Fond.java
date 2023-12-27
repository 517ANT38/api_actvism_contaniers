package com.charity.activism.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity
@Data
public class Fond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    private String token;

    private String name;

    @ManyToOne
    @JoinColumn(name = "typeFondId", referencedColumnName = "id")
    private TypeFond typeFond;

    @OneToMany(mappedBy = "fond")
    @JsonIgnore
    private List<UserActivismFond> userActivismFonds;

}
