package com.charity.activism.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity
@Data
public class TypeFond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "typeFond")
    @JsonIgnore
    private List<Fond> fonds;
}
