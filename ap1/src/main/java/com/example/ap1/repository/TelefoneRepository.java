package com.example.ap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ap1.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    
}