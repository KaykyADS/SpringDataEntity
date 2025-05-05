package com.atividade.SpringDataEntity.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atividade.SpringDataEntity.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	@Query("SELECT c FROM Consulta c WHERE c.data = :dataAgendamento")
	List<Consulta> findConsultaByData(LocalDate dataAgendamento);
	
	@Query(value = "SELECT COUNT(*) FROM Consulta c WHERE c.data = :data", nativeQuery = true)
    Long countConsultasByData(LocalDate data);
}
