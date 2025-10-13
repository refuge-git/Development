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
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeAtualizacaoDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.core.application.usecase.condicaosaude.*;
import school.sptech.refuge.core.domain.paginacao.Page;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeMapper;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeRequestDto;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeEntity;


import java.util.List;

@RestController
@RequestMapping("/condicoes-saude")
public class CondicaoSaudeController {

    private final CriarCondicaoSaudeUseCase criarCondicaoSaudeUseCase;
    private final ListarTodosCondicaoSaudeUseCase listarTodosCondicaoSaudeUseCase;
    private final AtualizarCondicaoSaudeUseCase atualizarCondicaoSaudeUseCase;
    private final DeletarCondicaoSaudeUseCase deletarCondicaoSaudeUseCase;
    private final BuscarCondicaoSaudeUseCase buscarCondicaoSaudeUseCase;
    private final ListagemCondicaoDeSaudeUseCase listagemCondicaoDeSaudeUseCase;
    private final ListarCondicaoSaudePorBeneficiarioUseCase listarCondicaoSaudePorBeneficiarioUseCase;

    public CondicaoSaudeController(CriarCondicaoSaudeUseCase criarCondicaoSaudeUseCase, ListarTodosCondicaoSaudeUseCase listarTodosCondicaoSaudeUseCase, AtualizarCondicaoSaudeUseCase atualizarCondicaoSaudeUseCase, DeletarCondicaoSaudeUseCase deletarCondicaoSaudeUseCase, BuscarCondicaoSaudeUseCase buscarCondicaoSaudeUseCase, ListagemCondicaoDeSaudeUseCase listagemCondicaoDeSaudeUseCase, ListarCondicaoSaudePorBeneficiarioUseCase listarCondicaoSaudePorBeneficiarioUseCase) {
        this.criarCondicaoSaudeUseCase = criarCondicaoSaudeUseCase;
        this.listarTodosCondicaoSaudeUseCase = listarTodosCondicaoSaudeUseCase;
        this.atualizarCondicaoSaudeUseCase = atualizarCondicaoSaudeUseCase;
        this.deletarCondicaoSaudeUseCase = deletarCondicaoSaudeUseCase;
        this.buscarCondicaoSaudeUseCase = buscarCondicaoSaudeUseCase;
        this.listagemCondicaoDeSaudeUseCase = listagemCondicaoDeSaudeUseCase;
        this.listarCondicaoSaudePorBeneficiarioUseCase = listarCondicaoSaudePorBeneficiarioUseCase;
    }

    @Operation(
            summary = "Cadastro de condição de saúde",
            description = "Recebe dados da condição de saúde e o transforma em entidade posteriormente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Condição de saúde cadastrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CondicaoSaudeRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados da condição de saúde inválidos ou ausentes", content = @Content)
    })

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CondicaoSaudeListDto> cadastrar(@Valid @RequestBody CondicaoSaudeRequestDto dto) {
        CondicaoSaudeListDto dtoCriada = criarCondicaoSaudeUseCase.execute(dto);
        return ResponseEntity.status(201).body(dtoCriada);
    }

    @Operation(
            summary = "Listar condições de saúde",
            description = "Retorna todas as condições de saúde cadastradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação bem-sucedida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CondicaoSaudeListDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há beneficiários cadastrados", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<CondicaoSaudeListDto>> listar() {
        List<CondicaoSaudeListDto> dtoListadas = listarTodosCondicaoSaudeUseCase.execute();
        return ResponseEntity.ok(dtoListadas);
    }

    @Operation(
            summary = "Buscar condição de saúde por id",
            description = "Retorna o beneficiário completo, dado o id passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condição de saúde encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CondicaoSaudeListDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CondicaoSaudeListDto> listarPorId(@PathVariable Integer id) {
        CondicaoSaudeListDto dto = buscarCondicaoSaudeUseCase.execute(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Atualização de beneficiário",
            description = "Atualiza os dados do beneficiário pelo ID passado e os dados presentes no body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condição de saúde atualizada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CondicaoSaudeListDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CondicaoSaudeListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody CondicaoSaudeAtualizacaoDto dto) {
        CondicaoSaudeListDto dtoAtualizado = atualizarCondicaoSaudeUseCase.execute(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @Operation(
            summary = "Excluir condição de saúde",
            description = "Exclui a condição de saúde dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Condição de saúde excluida com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        deletarCondicaoSaudeUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Listando quantidade limitada de condicão de saúde.",
            description = "Listar quantidade limitada de condicão de saúde com paginação."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condição de saúde encontradas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma condição de saúde  foi encontrada", content = @Content)
    })

    @GetMapping("/listar-page")
    public Page<CondicaoSaudeListDto> listarCondicaoSaude(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return listagemCondicaoDeSaudeUseCase.execute(page, size);
    }

    @GetMapping("/beneficiario/{idBeneficiario}")
    public ResponseEntity<List<CondicaoSaudeListDto>> listarPorBeneficiario(@PathVariable Integer idBeneficiario) {
        List<CondicaoSaudeListDto> condicoes = listarCondicaoSaudePorBeneficiarioUseCase.execute(idBeneficiario);

        if (condicoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(condicoes);
    }



}
