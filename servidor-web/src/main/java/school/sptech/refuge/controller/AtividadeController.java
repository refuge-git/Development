package school.sptech.refuge.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.entity.Atividade;
import school.sptech.refuge.service.AtividadeService;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    private final AtividadeService service;

    public AtividadeController(AtividadeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Atividade> cadastarAtividade(@Valid @RequestBody Atividade atividade) {
        Atividade criada = service.cadastrar(atividade);
        return ResponseEntity.status(201).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> listar() {
        List<Atividade> atividades = service.listar();

        if (atividades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> atualizar(@PathVariable Integer id,
                                               @Valid @RequestBody Atividade atividade) {
        return service.atualizar(id, atividade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean removido = service.deletar(id);

        if (removido) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
