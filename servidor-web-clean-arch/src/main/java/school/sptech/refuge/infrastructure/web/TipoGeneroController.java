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
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.usecase.tipogenero.*;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroRequestDto;

import java.util.List;

@RestController
@RequestMapping("/tipos_generos")
public class TipoGeneroController {

    private final CriarTipoGeneroUseCase criarTipoGeneroUseCase;
    private final ListarTodosTipoGeneroUseCase listarTodosTipoGeneroUseCase;
    private final BuscarTipoGeneroPorIdUseCase buscarTipoGeneroPorIdUseCase;
    private final AtualizarTipoGeneroUseCase atualizarTipoGeneroUseCase;
    private final DeletarTipoGeneroUseCase deletarTipoGeneroUseCase;

    public TipoGeneroController(CriarTipoGeneroUseCase criarTipoGeneroUseCase, ListarTodosTipoGeneroUseCase listarTodosTipoGeneroUseCase, BuscarTipoGeneroPorIdUseCase buscarTipoGeneroPorIdUseCase, AtualizarTipoGeneroUseCase atualizarTipoGeneroUseCase, DeletarTipoGeneroUseCase deletarTipoGeneroUseCase) {
        this.criarTipoGeneroUseCase = criarTipoGeneroUseCase;
        this.listarTodosTipoGeneroUseCase = listarTodosTipoGeneroUseCase;
        this.buscarTipoGeneroPorIdUseCase = buscarTipoGeneroPorIdUseCase;
        this.atualizarTipoGeneroUseCase = atualizarTipoGeneroUseCase;
        this.deletarTipoGeneroUseCase = deletarTipoGeneroUseCase;
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
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoGeneroListDto> cadastrar(@Valid @RequestBody TipoGeneroRequestDto dto) {
        TipoGeneroListDto criado = criarTipoGeneroUseCase.execute(dto);
        return ResponseEntity.status(201).body(criado);
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
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<TipoGeneroListDto>> listar() {
        List<TipoGeneroListDto> dtos = listarTodosTipoGeneroUseCase.execute();
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
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
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoGeneroListDto> listarPorId(@PathVariable Integer id) {
        TipoGeneroListDto dto = buscarTipoGeneroPorIdUseCase.execute(id);
        return ResponseEntity.ok(dto);
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
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoGeneroListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody TipoGeneroRequestDto dto) {
        TipoGeneroListDto dtoAtualizado = atualizarTipoGeneroUseCase.execute(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }


    @Operation(
            summary = "Excluir gênero",
            description = "Exclui o gênero dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Gênero excluido com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        deletarTipoGeneroUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
