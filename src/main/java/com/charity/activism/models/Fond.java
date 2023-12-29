package com.charity.activism.models;

import jakarta.persistence.*;
import lombok.Data;

import static org.hibernate.annotations.CascadeType.ALL;

import java.util.List;

import org.hibernate.annotations.Cascade;


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
    @Cascade({ALL})
    private TypeFond typeFond;

    @OneToMany(mappedBy = "fond")
    private List<UserActivismFond> userActivismFonds;

}
