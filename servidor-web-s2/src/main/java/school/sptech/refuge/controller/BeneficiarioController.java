package school.sptech.refuge.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.dto.beneficiario.BeneficiarioAtualizacaoDto;
import school.sptech.refuge.dto.beneficiario.BeneficiarioMapper;
import school.sptech.refuge.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.service.BeneficiarioService;


import java.util.List;

@RestController
@RequestMapping("/beneficiarios") // URI do servidor
public class BeneficiarioController {


    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
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
        Beneficiario beneficiario = BeneficiarioMapper.toEntity(dto, dto.getFuncionario(), dto.getTipoGenero(), dto.getEndereco());
        Beneficiario beneficiarioCadastrado = beneficiarioService.cadastrar(beneficiario);
        BeneficarioListDto dtoSalvo = BeneficiarioMapper.toListagemDto(beneficiarioCadastrado);
        return ResponseEntity.status(201).body(dtoSalvo);
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
        List<Beneficiario> beneficiarios = beneficiarioService.listar();
        if (beneficiarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<BeneficarioListDto> dtos = BeneficiarioMapper.toListagemDtos(beneficiarios);
        return ResponseEntity.status(200).body(dtos);
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
        Beneficiario beneficiario = beneficiarioService.buscarPorId(id);
        BeneficarioListDto dto = BeneficiarioMapper.toListagemDto(beneficiario);
        return ResponseEntity.status(200).body(dto);
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
        Beneficiario beneficiario = BeneficiarioMapper.toEntity(dto, id, dto.getFuncionario(), dto.getTipoGenero(), dto.getEndereco());
        Beneficiario beneficiarioAtualizado = beneficiarioService.atualizar(beneficiario);
        BeneficarioListDto dtoAtualizado = BeneficiarioMapper.toListagemDto(beneficiarioAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }


    /*@Operation(
            summary = "Beneficiários por gênero",
            description = "Listar todos os beneficiários pelo gênero especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por gênero encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o gênero especificado encontrado", content = @Content)
    })
    @GetMapping("/genero")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarPorGenero(@RequestParam String genero) {
        List<Beneficiario> beneficiario = beneficiarioService.listarPorGenero(genero);
        if (beneficiario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiario);
        return ResponseEntity.status(200).body(dto);
    }*/

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
        List<Beneficiario> beneficiario = beneficiarioService.listarPorRaca(raca);
        if (beneficiario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiario);
        return ResponseEntity.status(200).body(dto);
    }


    @Operation(
            summary = "Beneficiários por nome",
            description = "Listar todos os beneficiários pelo nome especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por nome encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o nome especificado foi encontrado", content = @Content)
    })
    @GetMapping("/nome")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarContendoNome(@RequestParam String nome) {
        List<Beneficiario> beneficiario = beneficiarioService.listarNome(nome);
        if (beneficiario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiario);
        return ResponseEntity.status(200).body(dto);
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
        beneficiarioService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }

}
