package school.sptech.refuge.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.dto.tiposexualidade.TipoSexualidadeMapper;
import school.sptech.refuge.dto.tiposexualidade.TipoSexualidadeRequestDto;
import school.sptech.refuge.core.domain.tiposexualidade.valueobject.TipoSexualidade;
import school.sptech.refuge.service.TipoSexualidadeService;

import java.util.List;

@RestController
@RequestMapping("/tipos_sexualidades")
public class TipoSexualidadeController {

    private final TipoSexualidadeService tipoSexualidadeService;

    public TipoSexualidadeController(TipoSexualidadeService tipoSexualidadeService) {
        this.tipoSexualidadeService = tipoSexualidadeService;
    }

    @Operation(
            summary = "Cadastrar uma sexualidade",
            description = "Cadastra uma sexualidade nova passado pelo body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sexualidade cadastrada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoSexualidadeListDto.class)))
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoSexualidadeListDto> cadastrar(@Valid @RequestBody TipoSexualidadeRequestDto dto) {
        TipoSexualidade tipoSexualidade = TipoSexualidadeMapper.toEntity(dto);
        TipoSexualidade tipoCadastrado = tipoSexualidadeService.cadastrar(tipoSexualidade);
        TipoSexualidadeListDto dtoSalvo = TipoSexualidadeMapper.toListagemDto(tipoCadastrado);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    @Operation(
            summary = "Listar sexualidades",
            description = "Lista todos os tipos de sexualidade cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos de sexualidade encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoSexualidadeListDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há tipo de sexualidade cadastrados", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<TipoSexualidadeListDto>> listar() {
        List<TipoSexualidade> tipos = tipoSexualidadeService.listar();
        if (tipos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<TipoSexualidadeListDto> dtos = TipoSexualidadeMapper.toListagemDtos(tipos);
        return ResponseEntity.status(200).body(dtos);
    }


    @Operation(
            summary = "Listar tipos sexualidade por id",
            description = "Lista as sexualidades especificado dado o id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sexualidade encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoSexualidadeListDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoSexualidadeListDto> listarPorId(@PathVariable Integer id) {
        TipoSexualidade tipoSexualidade = tipoSexualidadeService.buscarPorId(id);
        TipoSexualidadeListDto dto = TipoSexualidadeMapper.toListagemDto(tipoSexualidade);
        return ResponseEntity.status(200).body(dto);
    }

    @Operation(
            summary = "Listar tipos de sexualidade por partes da descrição",
            description = "Lista tipos de sexualidade dado parte da descrição"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sexualidade encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoSexualidadeListDto.class)))
    })
    @GetMapping("/descricao")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<TipoSexualidadeListDto>> listarPorDescricao(@RequestParam String descricao) {
        List<TipoSexualidade> tipos = tipoSexualidadeService.buscarPorDescricao(descricao);
        if (tipos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<TipoSexualidadeListDto> dto = TipoSexualidadeMapper.toListagemDtos(tipos);
        return ResponseEntity.status(200).body(dto);
    }


    @Operation(
            summary = "Atualizar de sexualidade",
            description = "Atualiza o tipo de sexualidade dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de sexualidade atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoSexualidadeListDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoSexualidadeListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody TipoSexualidadeRequestDto dto) {
        TipoSexualidade tipoSexualidade = TipoSexualidadeMapper.toEntity(dto, id);
        TipoSexualidade tipoAtualizado = tipoSexualidadeService.atualizar(tipoSexualidade);
        TipoSexualidadeListDto dtoAtualizado = TipoSexualidadeMapper.toListagemDto(tipoAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }


    @Operation(
            summary = "Excluir o tipo de sexualidade",
            description = "Exclui o tipo de sexualidade dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de sexualidade excluido com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        tipoSexualidadeService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }
}
