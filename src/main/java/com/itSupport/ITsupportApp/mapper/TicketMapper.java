package com.itSupport.ITsupportApp.mapper;

import com.itSupport.ITsupportApp.dto.TicketDto;
import com.itSupport.ITsupportApp.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    @Mapping(source = "technicien.id", target = "technicien_id")
    @Mapping(source = "signalPanne.id", target = "signalPanne_id")
    TicketDto toDTO(Ticket ticket);
    @Mapping(source = "technicien_id", target = "technicien.id")
    @Mapping(source = "signalPanne_id", target = "signalPanne.id")
    Ticket toEntity(TicketDto ticketDTO);

    List<TicketDto> toDTO(List<Ticket> ticketList);
    List<Ticket> toEntity(List<TicketDto> ticketDTOList);
}
