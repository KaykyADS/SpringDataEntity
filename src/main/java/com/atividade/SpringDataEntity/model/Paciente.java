package com.atividade.SpringDataEntity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @Column(name = "NumBenef", nullable = false)
    private Long numBenef;
    @Column(name = "Nome", nullable = false)
    private String nome;
    @Column(name = "Rua", nullable = false)
    private String rua;
    @Column(name = "Cep", nullable = false)
    private String cep;
    @Column(name = "Complemento", nullable = true)
    private String complemento;
    @Column(name = "Telefone", nullable = false)
    private String telefone;
}
