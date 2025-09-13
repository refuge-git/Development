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
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioAtualizacaoDto;
import school.sptech.refuge.core.application.usecase.beneficiario.*;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.core.domain.beneficiario.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.SexoEnum;


import java.util.List;

@RestController
@RequestMapping("/beneficiarios") // URI do servidor
public class BeneficiarioController {


    private final CriarBeneficiarioUseCase criarBeneficiarioUseCase;
    private final ListarTodosBeneficiarioUseCase listarTodosBeneficiarioUseCase;
    private final BuscarBeneficiarioUseCase buscarBeneficiarioUseCase;
    private final AtualizarBeneficiarioUseCase atualizarBeneficiarioUseCase;
    private final DeletarBeneficiarioUseCase deletarBeneficiarioUseCase;
    private final ListarBeneficiarioPorRacaUseCase listarBeneficiarioPorRacaUseCase;
    private final ListarBeneficiarioPorStatusUseCase listarBeneficiarioPorStatusUseCase;
    private final ListarBeneficiariosPorNomeRegistroOuSocialUseCase listarBeneficiarioPorNomeUse;

    public BeneficiarioController(CriarBeneficiarioUseCase criarBeneficiarioUseCase, ListarTodosBeneficiarioUseCase listarTodosBeneficiarioUseCase, BuscarBeneficiarioUseCase buscarBeneficiarioUseCase, AtualizarBeneficiarioUseCase atualizarBeneficiarioUseCase, DeletarBeneficiarioUseCase deletarBeneficiarioUseCase, ListarBeneficiarioPorRacaUseCase listarBeneficiarioPorRacaUseCase, ListarBeneficiarioPorStatusUseCase listarBeneficiarioPorStatusUseCase, ListarBeneficiariosPorNomeRegistroOuSocialUseCase listarBeneficiarioPorNomeUse) {
        this.criarBeneficiarioUseCase = criarBeneficiarioUseCase;
        this.listarTodosBeneficiarioUseCase = listarTodosBeneficiarioUseCase;
        this.buscarBeneficiarioUseCase = buscarBeneficiarioUseCase;
        this.atualizarBeneficiarioUseCase = atualizarBeneficiarioUseCase;
        this.deletarBeneficiarioUseCase = deletarBeneficiarioUseCase;
        this.listarBeneficiarioPorRacaUseCase = listarBeneficiarioPorRacaUseCase;
        this.listarBeneficiarioPorStatusUseCase = listarBeneficiarioPorStatusUseCase;
        this.listarBeneficiarioPorNomeUse = listarBeneficiarioPorNomeUse;
    }

    @Operation(
            summary = "Cadastro de beneficiário",
            description = "Recebe os dados do beneficiário pelo body e o transforma em entidade posteriormente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beneficiário cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados de beneficiário inválidos ou ausente", content = @Content)
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<BeneficarioListDto> cadastrar(@Valid @RequestBody BeneficiarioRequestDto dto) {
        BeneficarioListDto criado = criarBeneficiarioUseCase.execute(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @Operation(
            summary = "Listar beneficiários",
            description = "Retorna todos os beneficiários cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação bem-sucedida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficarioListDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há beneficiários cadastrados", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listar() {
        List<BeneficarioListDto> dtos = listarTodosBeneficiarioUseCase.execute();
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }


    @Operation(
            summary = "Buscar beneficiário por id",
            description = "Retorna o beneficiário completo, dado o id passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficarioListDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<BeneficarioListDto> listarPorId(@PathVariable Integer id) {
        BeneficarioListDto dto = buscarBeneficiarioUseCase.execute(id);
        return ResponseEntity.ok(dto);
    }


    @Operation(
            summary = "Atualização de beneficiário",
            description = "Atualiza os dados do beneficiário pelo id passado e os dados presentes no body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiário atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficarioListDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<BeneficarioListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody BeneficiarioAtualizacaoDto dto) {
        BeneficarioListDto dtoAtualizado = atualizarBeneficiarioUseCase.execute(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }


    @Operation(
            summary = "Beneficiários por status",
            description = "Listar todos os beneficiários pelo status especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por status encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o status especificado foi encontrado", content = @Content)
    })
    @GetMapping("/status")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarPorStatus(@RequestParam String raca) {
        List<BeneficarioListDto> dtos = listarBeneficiarioPorRacaUseCase.execute(raca);
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }

    @Operation(
            summary = "Beneficiários por raça",
            description = "Listar todos os beneficiários pela raça especificada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por raça encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com a raça especificada foi encontrado", content = @Content)
    })
    @GetMapping("/raca")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarPorRaca(@RequestParam String raca) {
        List<BeneficarioListDto> dtos = listarBeneficiarioPorRacaUseCase.execute(raca);
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }


    @Operation(
            summary = "Beneficiários por nome de registro.",
            description = "Listar todos os beneficiários pelo nome de registro especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por nome de registro encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o nome de registro especificado foi encontrado", content = @Content)
    })
    @GetMapping("/nome")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarContendoNomeRegistroOuNomeSocial(@RequestParam String nome) {
        List<BeneficarioListDto> dtos = listarBeneficiarioPorNomeUse.execute(nome);
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }


    @Operation(
            summary = "Exclusão de beneficiário",
            description = "Excluir beneficiário, dado o id passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Beneficiários excluido com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        deletarBeneficiarioUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
