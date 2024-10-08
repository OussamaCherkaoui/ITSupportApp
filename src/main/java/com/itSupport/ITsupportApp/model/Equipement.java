package com.itSupport.ITsupportApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nom;
    @Column
    private String marque;
    @Column
    private String modele;
    @Column
    private LocalDate dateAchat;
    @Column
    private LocalDate dateDerniereReparation;
    @Column
    private String etat;
    @Column
    private String picture;
}
