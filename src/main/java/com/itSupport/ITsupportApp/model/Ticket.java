package com.itSupport.ITsupportApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime DateOuverture;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "signalPanne_id")
    private SignalPanne signalPanne;
    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private Technicien technicien;
}
