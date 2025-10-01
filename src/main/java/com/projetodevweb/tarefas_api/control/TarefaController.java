package com.projetodevweb.tarefas_api.control;

import com.projetodevweb.tarefas_api.model.Tarefa;
import com.projetodevweb.tarefas_api.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController               
@RequestMapping("/tarefas")    
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        tarefa.setConcluida(false);
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return new ResponseEntity<>(tarefaSalva, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

}
