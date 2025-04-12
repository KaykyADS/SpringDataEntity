package com.atividade.SpringDataEntity.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "Medico")
public class Medico {
    @Id
    @Column(name = "Codigo", nullable = false)
    private String codigo;
    @Column(name = "Nome", nullable = false)
    private String nome;
    @Column(name = "Rua", nullable = false)
    private String rua;
    @Column(name = "Numero", nullable = false)
    private int numero;
    @Column(name = "Cep", nullable = false)
    private int cep;
    @Column(name = "Complemento", nullable = false)
    private String complemento;
    @Column(name = "Contato", nullable = false)
    private int contato;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Especialidade.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "IdEspecialidade", nullable = false)
    private Especialidade especialidade;
}
