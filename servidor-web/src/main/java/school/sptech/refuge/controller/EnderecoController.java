package school.sptech.refuge.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Endereco;
import school.sptech.refuge.service.EnderecoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody @Valid Endereco endereco) {
        Endereco novo = service.cadastrar(endereco);
        return ResponseEntity.status(201).body(novo); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listar() {
        List<Endereco> lista = service.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(lista); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Integer id) {

        Optional<Endereco> endereco = service.buscarPorId(id);

        if(endereco.isEmpty()){
            return ResponseEntity.status(404).build(); // Nenhum usuário encontrado
        }

        return ResponseEntity.status(200).body(endereco.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Integer id,
                                              @RequestBody @Valid Endereco endereco) {
        return service.atualizar(id, endereco)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404
    }

}
