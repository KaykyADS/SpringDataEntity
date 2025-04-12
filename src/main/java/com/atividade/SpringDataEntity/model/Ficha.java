package com.atividade.SpringDataEntity.model;

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
@Table(name = "Ficha")
@IdClass(Ficha.class)
public class Ficha {
    @Id
    @Column(name = "IdFicha", nullable = false)
    private int idFicha;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Paciente.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPaciente", nullable = false)
    private Paciente paciente;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Medico.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CodigoMedico", nullable = false)
    private Medico medico;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Especialidade.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "EspecialidadeId", nullable = false)
    private Especialidade especialidade;

    @Column(name = "Observacoes", nullable = false)
    private String observacoes;
}
