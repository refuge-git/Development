package school.sptech.refuge.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.entity.CondicaoSaude;
import school.sptech.refuge.service.CondicaoSaudeService;

import java.util.List;

@RestController
@RequestMapping("/condicoes-saude")
public class CondicaoSaudeController {

    private final CondicaoSaudeService service;

    public CondicaoSaudeController(CondicaoSaudeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CondicaoSaude> salvar(@RequestBody @Valid CondicaoSaude condicao) {
        CondicaoSaude nova = service.salvar(condicao);
        return ResponseEntity.status(201).body(nova);
    }

    @GetMapping
    public ResponseEntity<List<CondicaoSaude>> listar() {
        List<CondicaoSaude> lista = service.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondicaoSaude> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondicaoSaude> atualizar(@PathVariable Integer id,
                                                   @RequestBody @Valid CondicaoSaude condicao) {
        return service.atualizar(id, condicao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
