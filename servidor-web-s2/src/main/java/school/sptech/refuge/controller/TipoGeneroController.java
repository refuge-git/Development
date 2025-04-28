package school.sptech.refuge.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.dto.tipogenero.TipoGeneroMapper;
import school.sptech.refuge.dto.tipogenero.TipoGeneroRequestDto;
import school.sptech.refuge.entity.TipoGenero;
import school.sptech.refuge.service.TipoGeneroService;

import java.util.List;

@RestController
@RequestMapping("/tipos_generos")
public class TipoGeneroController {

    private final TipoGeneroService tipoGeneroService;

    public TipoGeneroController(TipoGeneroService tipoGeneroService) {
        this.tipoGeneroService = tipoGeneroService;
    }

    @PostMapping
    public ResponseEntity<TipoGeneroListDto> cadastrar(@Valid @RequestBody TipoGeneroRequestDto dto) {
        TipoGenero tipoGenero = TipoGeneroMapper.toEntity(dto);
        TipoGenero tipoCadastrado = tipoGeneroService.cadastrar(tipoGenero);
        TipoGeneroListDto dtoSalvo = TipoGeneroMapper.toListagemDto(tipoCadastrado);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<TipoGeneroListDto>> listar() {
        List<TipoGenero> tipos = tipoGeneroService.listar();
        if (tipos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<TipoGeneroListDto> dtos = TipoGeneroMapper.toListagemDtos(tipos);
        return ResponseEntity.status(200).body(dtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TipoGeneroListDto> listarPorId(@PathVariable Integer id) {
        TipoGenero tipoGenero = tipoGeneroService.buscarPorId(id);
        TipoGeneroListDto dto = TipoGeneroMapper.toListagemDto(tipoGenero);
        return ResponseEntity.status(200).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TipoGeneroListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody TipoGeneroRequestDto dto) {
        TipoGenero tipoGenero = TipoGeneroMapper.toEntity(dto, id);
        TipoGenero tipoAtualizado = tipoGeneroService.atualizar(tipoGenero);
        TipoGeneroListDto dtoAtualizado = TipoGeneroMapper.toListagemDto(tipoAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        tipoGeneroService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }
}
