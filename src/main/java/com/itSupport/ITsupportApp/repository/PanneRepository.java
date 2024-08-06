package com.itSupport.ITsupportApp.repository;

import com.itSupport.ITsupportApp.model.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanneRepository extends JpaRepository<Panne, Long> {
}
