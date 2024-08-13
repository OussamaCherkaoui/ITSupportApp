package com.itSupport.ITsupportApp.repository;


import com.itSupport.ITsupportApp.model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {
    @Query(value = "select e.* from signal_panne s inner join equipement e on e.id=s.equipement_id and s.id=:id",nativeQuery = true)
    Equipement findByIdSignalPanne(Long id);
}
