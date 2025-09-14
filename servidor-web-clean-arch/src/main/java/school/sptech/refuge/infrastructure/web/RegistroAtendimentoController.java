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
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoMapper;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoResponseDto;
import school.sptech.refuge.core.application.usecase.registroAtendimento.*;

import java.util.List;

@RestController
@RequestMapping("/registros-atendimentos")
public class RegistroAtendimentoController {

    private final AtualizarRegistroAtendimentoUseCase atualizarRegistroAtendimentoUseCase;
    private final CriarRegistroAtendimentoUseCase criarRegistroAtendimentoUseCase;
    private final DeletarRegistroAtendimentoUseCase deletarRegistroAtendimentoUseCase;
    private final ListarTodosRegistroAtendimentoUseCase listarTodosRegistroAtendimentoUseCase;
    private final BuscarRegistroAtendimentoUseCase buscarRegistroAtendimentoUseCase;

    public RegistroAtendimentoController(AtualizarRegistroAtendimentoUseCase atualizarRegistroAtendimentoUseCase, CriarRegistroAtendimentoUseCase criarRegistroAtendimentoUseCase, DeletarRegistroAtendimentoUseCase deletarRegistroAtendimentoUseCase, ListarTodosRegistroAtendimentoUseCase listarTodosRegistroAtendimentoUseCase, BuscarRegistroAtendimentoUseCase buscarRegistroAtendimentoUseCase) {
        this.atualizarRegistroAtendimentoUseCase = atualizarRegistroAtendimentoUseCase;
        this.criarRegistroAtendimentoUseCase = criarRegistroAtendimentoUseCase;
        this.deletarRegistroAtendimentoUseCase = deletarRegistroAtendimentoUseCase;
        this.listarTodosRegistroAtendimentoUseCase = listarTodosRegistroAtendimentoUseCase;
        this.buscarRegistroAtendimentoUseCase = buscarRegistroAtendimentoUseCase;
    }

    @Operation(
            summary = "Cadastro do registro de atendimento",
            description = "Recebe os dados do registro de atendimento pelo body e o transforma em entidade posteriormente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro de atendimento cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados de registro de atendimento inválidos ou ausente", content = @Content)
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<RegistroAtendimentoResponseDto> cadastrar(@Valid @RequestBody RegistroAtendimentoRequestDto dto) {
        RegistroAtendimentoResponseDto dtoSalvo = criarRegistroAtendimentoUseCase.execute(dto);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    @Operation(
            summary = "Listar os registros cadastrados",
            description = "Retorna todos os registros cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação bem-sucedida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há registros de atendimento cadastrados", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<RegistroAtendimentoResponseDto>> listar() {
        List<RegistroAtendimentoResponseDto> registroAtendimentos = listarTodosRegistroAtendimentoUseCase.execute();
        if (registroAtendimentos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(registroAtendimentos);
    }


    @Operation(
            summary = "Buscar registro de atendimento por id",
            description = "Retorna o registro de atendimento completo, dado o id passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de atendimento encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<RegistroAtendimentoResponseDto> listarPorId(@PathVariable Integer id) {
        RegistroAtendimentoResponseDto dto = buscarRegistroAtendimentoUseCase.execute(id);
        return ResponseEntity.status(200).body(dto);
    }


    @Operation(
            summary = "Atualização de registro de atendimento",
            description = "Atualiza os dados do registro de atendimento pelo id passado e os dados presentes no body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de atendimento atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoResponseDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<RegistroAtendimentoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody RegistroAtendimentoRequestDto dto) {
        RegistroAtendimentoResponseDto dtoAtualizado = atualizarRegistroAtendimentoUseCase.execute(id, dto);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }

    @Operation(
            summary = "Excluir registro de atendimento",
            description = "Exclui o registro de atendimento dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro de atendimento excluida com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        deletarRegistroAtendimentoUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }
}
