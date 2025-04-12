package com.atividade.SpringDataEntity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividade.SpringDataEntity.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    
}
