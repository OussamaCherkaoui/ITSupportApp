package com.itSupport.ITsupportApp.repository;

import com.itSupport.ITsupportApp.dto.TicketDto;
import com.itSupport.ITsupportApp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
     List<Ticket> findByTechnicien_Id(Long technicienId);
    @Query(value = "select T.date_ouverture,T.description,S.etat from ticket T inner join signal_panne S inner join user U on S.id=T.signal_panne_id and S.user_id=U.id and U.id=:userId",nativeQuery = true)
    List<Ticket> findByIdUser(Long userId);

    List<TicketDto> findBySignalPanne_Panne_Id(Long id);
}
