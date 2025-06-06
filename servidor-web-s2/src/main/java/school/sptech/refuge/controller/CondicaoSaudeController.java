package school.sptech.refuge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.dto.condicaosaude.CondicaoSaudeAtualizacaoDto;
import school.sptech.refuge.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.dto.condicaosaude.CondicaoSaudeMapper;
import school.sptech.refuge.dto.condicaosaude.CondicaoSaudeRequestDto;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Categoria;
import school.sptech.refuge.entity.CondicaoSaude;
import school.sptech.refuge.service.CondicaoSaudeService;

import java.util.List;

@RestController
@RequestMapping("/condicoesSaude")
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
    public ResponseEntity<CondicaoSaudeListDto> cadastrar(@Valid @RequestBody CondicaoSaudeRequestDto dto, Beneficiario beneficiario, Categoria categoria) {
         CondicaoSaude condicaoSaude = CondicaoSaudeMapper.toEntity(dto);
         CondicaoSaude condicaoSaudeCadastrada = condicaoSaudeService.cadastrar(condicaoSaude);
         CondicaoSaudeListDto dtoSalvo = CondicaoSaudeMapper.toListagemDto(condicaoSaudeCadastrada);
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
        List<CondicaoSaude> condicoesSaude = condicaoSaudeService.listar();
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
        CondicaoSaude condicaoSaude = condicaoSaudeService.buscarPorId(id);
        CondicaoSaudeListDto dto = CondicaoSaudeMapper.toListagemDto(condicaoSaude);
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
        CondicaoSaude condicaoSaude = CondicaoSaudeMapper.toEntity(dto, id);
        CondicaoSaude condicaoSaudeAtualizado = condicaoSaudeService.atualizar(condicaoSaude);
        CondicaoSaudeListDto dtoAtualizado = CondicaoSaudeMapper.toListagemDto(condicaoSaudeAtualizado);
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
    @GetMapping("/condicoesSaude/descricao")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<CondicaoSaudeListDto>> listarPorDescricao(@RequestParam String descricao) {
        List<CondicaoSaude> condicoesSaude = condicaoSaudeService.listarPorDescricao(descricao);
        if (condicoesSaude.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<CondicaoSaudeListDto> dto = CondicaoSaudeMapper.toListagemDtos(condicoesSaude);
        return ResponseEntity.status(200).body(dto);
    }

    @Operation(
            summary = "Condição de saúde por data de registro.",
            description = "Listar a condição de saúde por data de registro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por nome de registro encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o nome de registro especificado foi encontrado", content = @Content)
    })

}
