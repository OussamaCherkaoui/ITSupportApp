package com.itSupport.ITsupportApp.repository;

import com.itSupport.ITsupportApp.model.SignalPanne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignalRepository extends JpaRepository<SignalPanne, Long> {
    List<SignalPanne> findByUser_Id(Long id);
    List<SignalPanne> findByEquipement_Id(Long id);
    @Query(value = "select s.* from signal_panne s inner join ticket t inner join technicien te on s.id=t.signal_panne_id and t.technicien_id=:idTechnicien",nativeQuery = true)
    List<SignalPanne> findByIdTechnicien(Long idTechnicien);
    @Query(value = "select s.* from signal_panne s inner join ticket t on s.id=t.signal_panne_id and t.id=:idTicket",nativeQuery = true)
    SignalPanne findByIdTicket(Long idTicket);
}
