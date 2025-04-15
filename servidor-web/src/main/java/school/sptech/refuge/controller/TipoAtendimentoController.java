package school.sptech.refuge.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoAtualizarDto;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoListDto;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoMapper;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.entity.TipoAtendimento;
import school.sptech.refuge.service.TipoAtendimentoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipo-atendimento")
public class TipoAtendimentoController {

    private final TipoAtendimentoService tipoAtendimentoService;

    public TipoAtendimentoController(TipoAtendimentoService tipoAtendimentoService) {
        this.tipoAtendimentoService = tipoAtendimentoService;
    }

    @PostMapping
    public ResponseEntity<TipoAtendimentoListDto> cadastrar(@Valid @RequestBody TipoAtendimentoRequestDto dto) {
        Funcionario funcionario = tipoAtendimentoService.buscarFuncionarioPorId(dto.getIdFuncionario());
        TipoAtendimento tipoAtendimento = TipoAtendimentoMapper.toEntity(dto, funcionario);
        TipoAtendimento salvo = tipoAtendimentoService.cadastrar(tipoAtendimento);
        return ResponseEntity.ok(TipoAtendimentoMapper.toListagemDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<TipoAtendimentoListDto>> listarTodos() {
        List<TipoAtendimento> tipos = tipoAtendimentoService.listar();

        if (tipos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<TipoAtendimentoListDto> tiposListados = TipoAtendimentoMapper.toListagemDto(tipos);
        return ResponseEntity.ok(tiposListados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAtendimentoListDto> buscarPorId(@PathVariable Integer id) {
        TipoAtendimento tipo = tipoAtendimentoService.buscarTipoPorId(id);
        TipoAtendimentoListDto dtoList = TipoAtendimentoMapper.toListagemDto(tipo);
        return ResponseEntity.ok(dtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoAtendimentoListDto> atualizar(@PathVariable Integer id, @RequestBody TipoAtendimentoAtualizarDto dto) {
        Funcionario funcionario = tipoAtendimentoService.buscarFuncionarioPorId(id);
        TipoAtendimento tipo = TipoAtendimentoMapper.toEntity(dto, id, funcionario);
        TipoAtendimento tipoAtualizado = tipoAtendimentoService.atualizar(tipo);
        TipoAtendimentoListDto dtoList = TipoAtendimentoMapper.toListagemDto(tipoAtualizado);
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        tipoAtendimentoService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }
}
