package com.itSupport.ITsupportApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignalPanne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime dateEnregistrement;
    @Column
    private String etat;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "equipement_id")
    private Equipement equipement;
    @ManyToOne
    @JoinColumn(name = "panne_id")
    private Panne panne;
    @OneToMany(mappedBy = "signalPanne", fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();
}
