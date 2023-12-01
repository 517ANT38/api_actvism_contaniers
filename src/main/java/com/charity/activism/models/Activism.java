package com.charity.activism.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    private List<UserActivismFond> userActivismFonds;

}
