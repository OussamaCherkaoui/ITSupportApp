package com.itSupport.ITsupportApp.service;


import com.hello_event.dto.TicketDTO;
import com.hello_event.exception.DatabaseEmptyException;
import com.hello_event.mapper.TicketMapper;
import com.hello_event.model.Ticket;
import com.hello_event.repository.TicketRepository;
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
    private final TicketMapper ticketMapper;

    public TicketDTO save(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket.setPurchaseDate(LocalDateTime.now());
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    public List<Ticket> getTicketsByIdUser(Long userId) {
        List<Ticket> tickets = ticketRepository.findAllByUser_IdUser(userId);
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
}
