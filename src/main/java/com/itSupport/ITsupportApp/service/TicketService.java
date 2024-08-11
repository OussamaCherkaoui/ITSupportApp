package com.itSupport.ITsupportApp.service;

import com.itSupport.ITsupportApp.dto.TicketDto;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.model.Technicien;
import com.itSupport.ITsupportApp.model.Ticket;
import com.itSupport.ITsupportApp.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TechnicienService technicienService;

    public Ticket save(Ticket ticket) {
        ticket.setDateOuverture(LocalDateTime.now());
        ticket = ticketRepository.save(ticket);
        return ticket;
    }
    public Ticket attribuerTechnicien(Ticket ticket,Long idTechnicien) {
        Technicien technicien = technicienService.getTechnicienById(idTechnicien);
        ticket.setTechnicien(technicien);
        ticket = ticketRepository.save(ticket);
        return ticket;
    }
    public List<Ticket> getAllTicketByTechnicien(Long idTechnicien) {
        List<Ticket> tickets = ticketRepository.findByTechnicien_Id(idTechnicien);
        if (tickets.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return tickets;
    }


    public List<Ticket> getTicketsByIdUser(Long userId) {
        List<Ticket> tickets = ticketRepository.findByIdUser(userId);
        if (tickets.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return tickets;
    }
    public List<Ticket> getAllTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        if (tickets.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return tickets;
    }

    public List<TicketDto> getAllTicketByIdSignalPanne(Long id) {
        List<TicketDto> tickets = ticketRepository.findBySignalPanne_Panne_Id(id);
        if (tickets.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return tickets;
    }
}
