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
@Table (name = "Especialidade")
public class Especialidade {
    @Id
    @Column(name = "Id", nullable = false)
    private Long id;
    @Column(name = "Nome", nullable = false)
    private String nome;
}
