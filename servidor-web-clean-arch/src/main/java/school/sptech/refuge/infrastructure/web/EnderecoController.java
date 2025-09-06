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

import school.sptech.refuge.core.application.dto.endereco.EnderecoRequestDto;
import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import school.sptech.refuge.core.application.usecase.endereco.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final CriarEnderecoUseCase criarEnderecoUseCase;
    private final ListarTodosEnderecosUseCase listarTodosEnderecosUseCase;
    private final BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase;
    private final AtualizarEnderecoUseCase atualizarEnderecoUseCase;
    private final DeletarEnderecoUseCase deletarEnderecoUseCase;

    public EnderecoController(CriarEnderecoUseCase criarEnderecoUseCase,
                              ListarTodosEnderecosUseCase listarTodosEnderecosUseCase,
                              BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase,
                              AtualizarEnderecoUseCase atualizarEnderecoUseCase,
                              DeletarEnderecoUseCase deletarEnderecoUseCase) {
        this.criarEnderecoUseCase = criarEnderecoUseCase;
        this.listarTodosEnderecosUseCase = listarTodosEnderecosUseCase;
        this.buscarEnderecoPorIdUseCase = buscarEnderecoPorIdUseCase;
        this.atualizarEnderecoUseCase = atualizarEnderecoUseCase;
        this.deletarEnderecoUseCase = deletarEnderecoUseCase;
    }

    @Operation(
            summary = "Cadastrar um endereço",
            description = "Cadastra um novo endereço passado pelo body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDto.class)))
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<EnderecoResponseDto> cadastrar(@Valid @RequestBody EnderecoRequestDto dto) {
        EnderecoResponseDto criado = criarEnderecoUseCase.executar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @Operation(
            summary = "Listar endereços",
            description = "Lista todos os endereços cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há endereços cadastrados", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<EnderecoResponseDto>> listar() {
        List<EnderecoResponseDto> dtos = listarTodosEnderecosUseCase.executar();
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }

    @Operation(
            summary = "Buscar endereço por id",
            description = "Retorna o endereço correspondente ao id informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<EnderecoResponseDto> buscarPorId(@PathVariable Integer id) {
        EnderecoResponseDto dto = buscarEnderecoPorIdUseCase.executar(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Atualizar endereço",
            description = "Atualiza os dados de um endereço pelo id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<EnderecoResponseDto> atualizar(@PathVariable Integer id,
                                                         @Valid @RequestBody EnderecoRequestDto dto) {
        EnderecoResponseDto atualizado = atualizarEnderecoUseCase.executar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
            summary = "Excluir endereço",
            description = "Exclui um endereço dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        deletarEnderecoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
