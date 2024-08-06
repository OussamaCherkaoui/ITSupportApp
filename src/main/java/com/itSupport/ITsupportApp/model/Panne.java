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
public class Panne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate dateCreation;
    @Column
    private String description;

    @OneToMany(mappedBy = "panne", fetch = FetchType.EAGER)
    private List<SignalPanne> signalPannes = new ArrayList<>();

}
