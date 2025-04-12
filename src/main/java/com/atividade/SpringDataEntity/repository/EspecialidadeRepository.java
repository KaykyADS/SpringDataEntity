package com.atividade.SpringDataEntity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividade.SpringDataEntity.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {
    
}
