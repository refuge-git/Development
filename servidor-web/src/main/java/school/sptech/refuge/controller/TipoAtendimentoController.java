package school.sptech.refuge.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoAtualizarDto;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoListDto;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.service.TipoAtendimentoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipo-atendimento")
public class TipoAtendimentoController {

    private final TipoAtendimentoService service;

    public TipoAtendimentoController(TipoAtendimentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TipoAtendimentoListDto> cadastrarTipoAtendimento(@Valid @RequestBody TipoAtendimentoRequestDto tipoAtendimentoRequestDTO) {
        return ResponseEntity.ok(service.cadastrar(tipoAtendimentoRequestDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TipoAtendimentoListDto> atualizarTipoAtendimento(@PathVariable Integer id, @RequestBody TipoAtendimentoAtualizarDto tipoAtendimentoRequestDTO) {
        return ResponseEntity.ok(service.atualizar(id, tipoAtendimentoRequestDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<TipoAtendimentoListDto> listarPorIdTipoAtendimento(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getTipoAtendimento(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoAtendimento(@PathVariable Integer id) {
        service.deleteTipoAtendimento(id);
        return ResponseEntity.noContent().build();
    }
}
