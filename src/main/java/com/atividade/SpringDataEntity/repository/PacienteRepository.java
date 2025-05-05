package com.atividade.SpringDataEntity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividade.SpringDataEntity.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	 List<Paciente> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
	 
	 Paciente findFirstByTelefone(String telefone);
}
