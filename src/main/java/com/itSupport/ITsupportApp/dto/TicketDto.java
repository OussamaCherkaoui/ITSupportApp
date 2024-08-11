package com.itSupport.ITsupportApp.dto;

import com.itSupport.ITsupportApp.model.SignalPanne;
import com.itSupport.ITsupportApp.model.Technicien;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {
    private Long id;
    private LocalDateTime DateOuverture;
    private String description;
    private Long signalPanne_id;
    private Long technicien_id;
}
