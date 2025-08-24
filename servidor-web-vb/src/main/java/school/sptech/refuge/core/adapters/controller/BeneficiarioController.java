package school.sptech.refuge.core.adapters.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.core.domain.beneficiario.valueobject.Beneficiario;
import school.sptech.refuge.core.domain.beneficiario.valueobject.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.valueobject.SexoEnum;
import school.sptech.refuge.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.dto.beneficiario.BeneficiarioAtualizacaoDto;
import school.sptech.refuge.dto.beneficiario.BeneficiarioMapper;
import school.sptech.refuge.dto.beneficiario.BeneficiarioRequestDto;
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
        Beneficiario beneficiario = BeneficiarioMapper.toEntity(dto);
        Beneficiario beneficiarioCadastrado = beneficiarioService.cadastrar(beneficiario);
        /*Beneficiario beneficiarioCompleto = beneficiarioService.buscarPorId(beneficiarioCadastrado.getId());*/
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
        Beneficiario beneficiario = BeneficiarioMapper.toEntity(dto, id);
        Beneficiario beneficiarioAtualizado = beneficiarioService.atualizar(beneficiario);
        BeneficarioListDto dtoAtualizado = BeneficiarioMapper.toListagemDto(beneficiarioAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }


    @Operation(
            summary = "Beneficiários por sexo",
            description = "Listar todos os beneficiários pelo sexo especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por sexo encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o sexo especificado encontrado", content = @Content)
    })
    @GetMapping("/sexo")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarPorSexo(@RequestParam SexoEnum sexo) {
        List<Beneficiario> beneficiario = beneficiarioService.listarPorSexo(sexo);
        if (beneficiario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiario);
        return ResponseEntity.status(200).body(dto);
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
    public ResponseEntity<List<BeneficarioListDto>> listarPorRaca(@RequestParam RacaEnum raca) {
        List<Beneficiario> beneficiario = beneficiarioService.listarPorRaca(raca);
        if (beneficiario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiario);
        return ResponseEntity.status(200).body(dto);
    }

    @Operation(
            summary = "Beneficiários por nome do tipo de gênero",
            description = "Listar todos os beneficiários pelo nome do tipo de gênero especificada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por tipo de gênero encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o tipo de gênero especificado foi encontrado", content = @Content)
    })
    @GetMapping("/beneficiarios/nome-genero")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarPorNomeGenero(@RequestParam String nomeGenero) {
        List<Beneficiario> beneficiarios = beneficiarioService.listarPorTipoGenero(nomeGenero);
        if (beneficiarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiarios);
        return ResponseEntity.status(200).body(dto);
    }

    @Operation(
            summary = "Beneficiários por nome do tipo de sexualidade",
            description = "Listar todos os beneficiários pelo nome do tipo de sexualidade especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por tipo de sexualidade encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o tipo de sexaulidade especificado foi encontrado", content = @Content)
    })
    @GetMapping("/beneficiarios/nome-sexualidade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarPorNomeSexualidade(@RequestParam String nomeSexualidade) {
        List<Beneficiario> beneficiarios = beneficiarioService.listarPorTipoSexualidade(nomeSexualidade);
        if (beneficiarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiarios);
        return ResponseEntity.status(200).body(dto);
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
    @GetMapping("/nome-registro")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarContendoNomeRegistro(@RequestParam String nome) {
        List<Beneficiario> beneficiario = beneficiarioService.listarNomeRegistro(nome);
        if (beneficiario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiario);
        return ResponseEntity.status(200).body(dto);
    }

    @Operation(
            summary = "Beneficiários por nome social.",
            description = "Listar todos os beneficiários pelo nome social especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários por nome de social encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum beneficiário com o nome social especificado foi encontrado", content = @Content)
    })
    @GetMapping("/nome-social")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BeneficarioListDto>> listarContendoNomeSocial(@RequestParam String nome) {
        List<Beneficiario> beneficiario = beneficiarioService.listarNomeSocial(nome);
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
