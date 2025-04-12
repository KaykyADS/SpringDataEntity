package com.atividade.SpringDataEntity.model;

import java.security.Timestamp;
import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(Consulta.class)
public class Consulta {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Paciente.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PacienteId", nullable = false)
    private Paciente paciente;
    @Id
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Medico.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "MedicoCodigo", nullable = false)
    private Medico medico;
    @Column(name = "Data", nullable = false)
    private Date data;
    @Column(name = "Hora", nullable = false)
    private Timestamp hora;
}
