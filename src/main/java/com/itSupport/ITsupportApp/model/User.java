package com.itSupport.ITsupportApp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
@DiscriminatorValue("USER")
public class User extends Utilisateur{
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<SignalPanne> signalPannes = new ArrayList<>();
}
