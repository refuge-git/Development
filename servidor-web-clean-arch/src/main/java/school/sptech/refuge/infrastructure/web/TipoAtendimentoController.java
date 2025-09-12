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
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.core.application.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.core.application.usecase.tipoAtendimento.AtualizarTipoAtendimentoUseCase;
import school.sptech.refuge.core.application.usecase.tipoAtendimento.CriarTipoAtendimentoUseCase;
import school.sptech.refuge.core.application.usecase.tipoAtendimento.DeletarTipoAtendimentoUseCase;
import school.sptech.refuge.core.application.usecase.tipoAtendimento.ListarTodosTipoAtendimentoUseCase;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoMapper;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.entity.TipoAtendimento;
import school.sptech.refuge.service.TipoAtendimentoService;

import java.util.List;

@RestController
@RequestMapping("/atendimentos")
public class TipoAtendimentoController {

    private final CriarTipoAtendimentoUseCase criarTipoAtendimentoUseCase;
    private final AtualizarTipoAtendimentoUseCase atualizarTipoAtendimentoUseCase;
    private final ListarTodosTipoAtendimentoUseCase listarTodosTipoAtendimentoUseCase;
    private final DeletarTipoAtendimentoUseCase deletarTipoAtendimentoUseCase;

    public TipoAtendimentoController(CriarTipoAtendimentoUseCase criarTipoAtendimentoUseCase, AtualizarTipoAtendimentoUseCase atualizarTipoAtendimentoUseCase, ListarTodosTipoAtendimentoUseCase listarTodosTipoAtendimentoUseCase, DeletarTipoAtendimentoUseCase deletarTipoAtendimentoUseCase) {
        this.criarTipoAtendimentoUseCase = criarTipoAtendimentoUseCase;
        this.atualizarTipoAtendimentoUseCase = atualizarTipoAtendimentoUseCase;
        this.listarTodosTipoAtendimentoUseCase = listarTodosTipoAtendimentoUseCase;
        this.deletarTipoAtendimentoUseCase = deletarTipoAtendimentoUseCase;
    }

    @Operation(
            summary = "Cadastro de tipo de atendimento",
            description = "Recebe os dados do tipo de atendimento pelo body e o transforma em entidade posteriormente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de atendimento cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoAtendimentoRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados de tipo de atendimento inválidos ou ausente", content = @Content)
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoAtendimentoResponseDto> cadastrar(@Valid @RequestBody TipoAtendimentoRequestDto dto) {
        TipoAtendimentoResponseDto dtoSalvo = criarTipoAtendimentoUseCase.execute(dto);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    @Operation(
            summary = "Listar os atendimentos registrados",
            description = "Retorna todos os atendimentos cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação bem-sucedida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoAtendimentoResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há atendimentos cadastrados", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<TipoAtendimentoResponseDto>> listar() {
        List<TipoAtendimentoResponseDto> tipoAtendimentos = listarTodosTipoAtendimentoUseCase.execute();
        if (tipoAtendimentos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(tipoAtendimentos);
    }

//
//    @Operation(
//            summary = "Buscar tipo de atendimento por id",
//            description = "Retorna o tipo de atendimento completo, dado o id passado"
//    )
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Tipo de atendimento encontrado",
//                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoAtendimentoResponseDto.class)))
//    })
//    @GetMapping("/{id}")
//    @SecurityRequirement(name = "Bearer")
//    public ResponseEntity<TipoAtendimentoResponseDto> listarPorId(@PathVariable Integer id) {
//        TipoAtendimento tipoAtendimento = tipoAtendimentoService.buscarPorId(id);
//        TipoAtendimentoResponseDto dto = TipoAtendimentoMapper.toListagemDto(tipoAtendimento);
//        return ResponseEntity.status(200).body(dto);
//    }


    @Operation(
            summary = "Atualização de tipo de atendimento",
            description = "Atualiza os dados do tipo de atendimento pelo id passado e os dados presentes no body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de atendimento atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoAtendimentoResponseDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TipoAtendimentoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody TipoAtendimentoRequestDto dto) {
        TipoAtendimentoResponseDto dtoAtualizado = atualizarTipoAtendimentoUseCase.execute(id, dto);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }

    @Operation(
            summary = "Excluir tipo de atendimento",
            description = "Exclui o tipo de atendimento dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de atendimento excluido com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        deletarTipoAtendimentoUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }
}
