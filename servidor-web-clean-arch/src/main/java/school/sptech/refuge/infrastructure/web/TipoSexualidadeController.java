package school.sptech.refuge.infrastructure.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.application.usecase.tiposexualidade.*;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeRequestDto;

import java.util.List;

@RestController
@RequestMapping("/tipos_sexualidades")
public class TipoSexualidadeController {

    private final CriarTipoSexualidadeUseCase criarTipoSexualidadeUseCase;
    private final ListarTodosTipoSexualidadeUseCase listarTodosTipoSexualidadeUseCase;
    private final BuscarTipoSexualidadePorIdUseCase buscarTipoSexualidadePorIdUseCase;
    private final AtualizarTipoSexualidadeUseCase atualizarTipoSexualidadeUseCase;
    private final DeletarTipoSexualidadeUseCase deletarTipoSexualidadeUseCase;

    public TipoSexualidadeController(CriarTipoSexualidadeUseCase criarTipoSexualidadeUseCase, ListarTodosTipoSexualidadeUseCase listarTodosTipoSexualidadeUseCase, BuscarTipoSexualidadePorIdUseCase buscarTipoSexualidadePorIdUseCase, AtualizarTipoSexualidadeUseCase atualizarTipoSexualidadeUseCase, DeletarTipoSexualidadeUseCase deletarTipoSexualidadeUseCase) {
        this.criarTipoSexualidadeUseCase = criarTipoSexualidadeUseCase;
        this.listarTodosTipoSexualidadeUseCase = listarTodosTipoSexualidadeUseCase;
        this.buscarTipoSexualidadePorIdUseCase = buscarTipoSexualidadePorIdUseCase;
        this.atualizarTipoSexualidadeUseCase = atualizarTipoSexualidadeUseCase;
        this.deletarTipoSexualidadeUseCase = deletarTipoSexualidadeUseCase;
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
        TipoSexualidadeListDto criado = criarTipoSexualidadeUseCase.execute(dto);
        return ResponseEntity.status(201).body(criado);
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
        List<TipoSexualidadeListDto> dtos = listarTodosTipoSexualidadeUseCase.execute();
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
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
        TipoSexualidadeListDto dto = buscarTipoSexualidadePorIdUseCase.execute(id);
        return ResponseEntity.ok(dto);
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
        TipoSexualidadeListDto dtoAtualizado = atualizarTipoSexualidadeUseCase.execute(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
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
        deletarTipoSexualidadeUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
