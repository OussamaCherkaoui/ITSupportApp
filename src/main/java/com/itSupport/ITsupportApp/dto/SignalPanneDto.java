package com.itSupport.ITsupportApp.dto;

import com.itSupport.ITsupportApp.model.Equipement;
import com.itSupport.ITsupportApp.model.Panne;
import com.itSupport.ITsupportApp.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignalPanneDto {

    private Long id;
    private LocalDateTime dateEnregistrement;
    private String etat;
    private Long user_id;
    private Long equipement_id;
    private Long panne_id;

}
