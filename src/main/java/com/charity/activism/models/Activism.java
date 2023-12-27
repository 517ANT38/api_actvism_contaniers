package com.charity.activism.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity
@Data
public class Activism {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double pay;

    @OneToMany(mappedBy = "activism")
    @JsonIgnore
    private List<UserActivismFond> userActivismFonds;

}
