package com.projetodevweb.tarefas_api.control;

import com.projetodevweb.tarefas_api.model.Tarefa;
import com.projetodevweb.tarefas_api.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        tarefa.setConcluida(false); // sempre começa como pendente
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return new ResponseEntity<>(tarefaSalva, HttpStatus.CREATED); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTodas(@RequestParam(required = false) String descricao) {
        List<Tarefa> tarefas;
        if (descricao != null && !descricao.isBlank()) {
            tarefas = tarefaRepository.findByDescricaoContaining(descricao);
        } else {
            tarefas = tarefaRepository.findAll();
        }

        if (tarefas.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setConcluida(tarefaAtualizada.isConcluida());
            tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
            Tarefa tarefaSalva = tarefaRepository.save(tarefa);
            return ResponseEntity.ok(tarefaSalva);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefaRepository.delete(tarefa);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> listarPendentes() {
        List<Tarefa> pendentes = tarefaRepository.findByConcluida(false);
        if (pendentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pendentes);
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setConcluida(true);
            Tarefa tarefaConcluida = tarefaRepository.save(tarefa);
            return ResponseEntity.ok(tarefaConcluida);
        }).orElse(ResponseEntity.notFound().build());
    }
}
