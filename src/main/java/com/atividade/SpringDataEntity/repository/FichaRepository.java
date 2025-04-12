package com.atividade.SpringDataEntity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividade.SpringDataEntity.model.Ficha;

public interface FichaRepository extends JpaRepository<Ficha, Integer> {
    
}
