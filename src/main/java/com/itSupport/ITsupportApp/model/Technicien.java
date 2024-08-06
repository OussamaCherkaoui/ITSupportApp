package com.itSupport.ITsupportApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("TECH")
public class Technicien extends Utilisateur {
    @Column
    private String specialite;
    @OneToMany(mappedBy = "technicien", fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();
}
