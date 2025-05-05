package com.atividade.SpringDataEntity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atividade.SpringDataEntity.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
	@Query("SELECT m FROM Medico m JOIN m.especialidade e WHERE e.id = :id")
    List<Medico> findByEspecialidade(Long id);
}
