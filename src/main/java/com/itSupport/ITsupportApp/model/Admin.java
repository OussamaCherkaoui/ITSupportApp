package com.itSupport.ITsupportApp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends Utilisateur {

}
