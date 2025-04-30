package school.sptech.refuge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.endereco.EnderecoListDto;
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

    @Operation(
            summary = "Cadastrar um gênero",
            description = "Cadastra um gênero novo passado pelo body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gênero cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoGeneroListDto.class)))
    })
    @PostMapping
    public ResponseEntity<TipoGeneroListDto> cadastrar(@Valid @RequestBody TipoGeneroRequestDto dto) {
        TipoGenero tipoGenero = TipoGeneroMapper.toEntity(dto);
        TipoGenero tipoCadastrado = tipoGeneroService.cadastrar(tipoGenero);
        TipoGeneroListDto dtoSalvo = TipoGeneroMapper.toListagemDto(tipoCadastrado);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    @Operation(
            summary = "Listar gêneros",
            description = "Lista todos os gêneros cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gêneros encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoGeneroListDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há gêneros cadastrados", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<TipoGeneroListDto>> listar() {
        List<TipoGenero> tipos = tipoGeneroService.listar();
        if (tipos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<TipoGeneroListDto> dtos = TipoGeneroMapper.toListagemDtos(tipos);
        return ResponseEntity.status(200).body(dtos);
    }


    @Operation(
            summary = "Listar gênero por id",
            description = "Lista o gênero especificado dado o id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoGeneroListDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoGeneroListDto> listarPorId(@PathVariable Integer id) {
        TipoGenero tipoGenero = tipoGeneroService.buscarPorId(id);
        TipoGeneroListDto dto = TipoGeneroMapper.toListagemDto(tipoGenero);
        return ResponseEntity.status(200).body(dto);
    }


    @Operation(
            summary = "Atualizar gênero",
            description = "Atualiza o gênero dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoGeneroListDto.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoGeneroListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody TipoGeneroRequestDto dto) {
        TipoGenero tipoGenero = TipoGeneroMapper.toEntity(dto, id);
        TipoGenero tipoAtualizado = tipoGeneroService.atualizar(tipoGenero);
        TipoGeneroListDto dtoAtualizado = TipoGeneroMapper.toListagemDto(tipoAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }


    @Operation(
            summary = "Excluir gênero",
            description = "Exclui o gênero dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Gênero excluido com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        tipoGeneroService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }
}
