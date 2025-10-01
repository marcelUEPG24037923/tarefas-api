package com.projetodevweb.tarefas_api.repository;

import com.projetodevweb.tarefas_api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByDescricaoContaining(String descricao);
    List<Tarefa> findByConcluida(boolean concluida);
}
