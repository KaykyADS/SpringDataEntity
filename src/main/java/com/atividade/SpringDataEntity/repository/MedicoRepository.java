package com.atividade.SpringDataEntity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividade.SpringDataEntity.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    
}
