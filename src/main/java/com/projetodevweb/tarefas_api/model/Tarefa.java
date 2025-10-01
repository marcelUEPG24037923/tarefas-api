package com.projetodevweb.tarefas_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false)
    private boolean concluida = false;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private int prioridade;

    public Long getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean isConcluida() {
        return this.concluida;
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

}
