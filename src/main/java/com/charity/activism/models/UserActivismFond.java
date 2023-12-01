package com.charity.activism.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table
@Entity
@Data
public class UserActivismFond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private ActivismUser user;


    @ManyToOne
    @JoinColumn(name = "activismId", referencedColumnName = "id")
    private Activism activism;

    @ManyToOne
    @JoinColumn(name = "fondId", referencedColumnName = "id")
    private Fond fond;

    private Date date;

    private int countHours;

    private boolean done;

    private boolean paid;

}
