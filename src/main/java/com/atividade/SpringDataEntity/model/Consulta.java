package com.atividade.SpringDataEntity.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Consulta")
public class Consulta {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConsulta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PacienteId", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MedicoCodigo", nullable = false)
    private Medico medico;

    @Column(name = "Data", nullable = false)
    private LocalDate data;

    @Column(name = "Hora", nullable = false)
    private LocalTime hora;
}
