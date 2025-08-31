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
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeAtualizacaoDto;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeMapper;
import school.sptech.refuge.core.application.dto.condicaosaude.CondicaoSaudeRequestDto;
import school.sptech.refuge.infrastructure.bd.condicaosaude.CondicaoSaudeEntity;
import school.sptech.refuge.antes.service.CondicaoSaudeService;

import java.util.List;

@RestController
@RequestMapping("/condicoes-saude")
public class CondicaoSaudeController {
private final CondicaoSaudeService condicaoSaudeService;

    public CondicaoSaudeController(CondicaoSaudeService condicaoSaudeService) {
        this.condicaoSaudeService = condicaoSaudeService;
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
         CondicaoSaudeEntity condicaoSaudeEntity = CondicaoSaudeMapper.toEntity(dto);
         CondicaoSaudeEntity condicaoSaudeEntityCadastrada = condicaoSaudeService.cadastrar(condicaoSaudeEntity);
         CondicaoSaudeListDto dtoSalvo = CondicaoSaudeMapper.toListagemDto(condicaoSaudeEntityCadastrada);
         return ResponseEntity.status(201).body(dtoSalvo);
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
        List<CondicaoSaudeEntity> condicoesSaude = condicaoSaudeService.listar();
        if (condicoesSaude.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<CondicaoSaudeListDto> dtos = CondicaoSaudeMapper.toListagemDtos(condicoesSaude);
         return ResponseEntity.status(200).body(dtos);
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
        CondicaoSaudeEntity condicaoSaudeEntity = condicaoSaudeService.buscarPorId(id);
        CondicaoSaudeListDto dto = CondicaoSaudeMapper.toListagemDto(condicaoSaudeEntity);
        return ResponseEntity.status(200).body(dto);
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
        CondicaoSaudeEntity condicaoSaudeEntity = CondicaoSaudeMapper.toEntity(dto, id);
        CondicaoSaudeEntity condicaoSaudeEntityAtualizado = condicaoSaudeService.atualizar(condicaoSaudeEntity);
        CondicaoSaudeListDto dtoAtualizado = CondicaoSaudeMapper.toListagemDto(condicaoSaudeEntityAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }

    @Operation(
            summary = "Condição de saúde por descrição",
            description = "Listar todas as condições de saúde pela descrição"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condições de saúde pela descrição encontradas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CondicaoSaudeRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma condição de saúde com a descrição especificada foi encontrado", content = @Content)
    })
    @GetMapping("/descricao")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<CondicaoSaudeListDto>> listarPorDescricao(@RequestParam String descricao) {
        List<CondicaoSaudeEntity> condicoesSaude = condicaoSaudeService.listarPorDescricao(descricao);
        if (condicoesSaude.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<CondicaoSaudeListDto> dto = CondicaoSaudeMapper.toListagemDtos(condicoesSaude);
        return ResponseEntity.status(200).body(dto);
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
        condicaoSaudeService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(
            summary = "Condição de saúde por id do beneficiário",
            description = "Listar todas as condições de saúde pelo id do beneficiário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Condições de saúde pelo id do beneficiário encontradas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CondicaoSaudeRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma condição de saúde com o id do beneficiário especificado foi encontrado", content = @Content)
    })
    @GetMapping("/beneficiario/{idBeneficiario}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<CondicaoSaudeListDto>> listarPorBeneficiarioId(@PathVariable Integer idBeneficiario) {
        List<CondicaoSaudeEntity> condicoesSaude = condicaoSaudeService.listarPorIdBeneficiario(idBeneficiario);
        if (condicoesSaude.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<CondicaoSaudeListDto> dto = CondicaoSaudeMapper.toListagemDtos(condicoesSaude);
        return ResponseEntity.status(200).body(dto);
    }



}
