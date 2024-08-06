package com.itSupport.ITsupportApp.repository;

import com.itSupport.ITsupportApp.model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
