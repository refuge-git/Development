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
import school.sptech.refuge.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.dto.endereco.EnderecoAtualizacaoDto;
import school.sptech.refuge.dto.endereco.EnderecoListDto;
import school.sptech.refuge.dto.endereco.EnderecoMapper;
import school.sptech.refuge.dto.endereco.EnderecoRequestDto;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Endereco;
import school.sptech.refuge.service.BeneficiarioService;
import school.sptech.refuge.service.EnderecoService;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {


    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Operation(
            summary = "Cadastro de endereço",
            description = "Recebe os dados do endereço pelo body e o transforma em entidade posteriormente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoRequestDto.class)))
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<EnderecoListDto> cadastrar(@Valid @RequestBody EnderecoRequestDto dto) {
        Endereco endereco = EnderecoMapper.toEntity(dto);
        Endereco enderecoCadastrado = enderecoService.cadastrar(endereco);
        EnderecoListDto dtoSalvo = EnderecoMapper.toListagemDto(enderecoCadastrado);
        return ResponseEntity.status(201).body(dtoSalvo);
   }

    @Operation(
            summary = "Listar endereços",
            description = "Retorna todos os endereços cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoListDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há endereços cadastrados", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<EnderecoListDto>> listar() {
        List<Endereco> enderecos = enderecoService.listar();
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<EnderecoListDto> dtos = EnderecoMapper.toListagemDtos(enderecos);
        return ResponseEntity.status(200).body(dtos);
    }



    @Operation(
            summary = "Listar endereço por id",
            description = "Retorna um endereço em específico dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoListDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<EnderecoListDto> listarPorId(@PathVariable Integer id) {
        Endereco endereco = enderecoService.buscarPorId(id);
        EnderecoListDto dto = EnderecoMapper.toListagemDto(endereco);
        return ResponseEntity.status(200).body(dto);
    }



    @Operation(
            summary = "Atualiza o endereço",
            description = "Atualiza o endereço dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoListDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<EnderecoListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody EnderecoAtualizacaoDto dto) {
        Endereco endereco = EnderecoMapper.toEntity(dto, id);
        Endereco enderecoAtualizado = enderecoService.atualizar(endereco);
        EnderecoListDto dtoAtualizado = EnderecoMapper.toListagemDto(enderecoAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }



    @Operation(
            summary = "Deleta o endereço",
            description = "Deleta o endereço dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço excluido com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        enderecoService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }



    @Operation(
            summary = "Listar o endereço por bairro",
            description = "Lista os endereços dado o bairro especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços encontrados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoListDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum endereço encontrado", content = @Content)
    })
    @GetMapping("/bairro")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<EnderecoListDto>> listarPorBairro(@RequestParam String bairro) {
        List<Endereco> enderecos = enderecoService.listarPorBairro(bairro);
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<EnderecoListDto> dtos = EnderecoMapper.toListagemDtos(enderecos);
        return ResponseEntity.status(200).body(dtos);
    }

    @Operation(
            summary = "Listar o endereço por nome do logradouro",
            description = "Lista os endereços dado o nome do logradouro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços encontrados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoListDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum endereço encontrado", content = @Content)
    })
    @GetMapping("/nome_logradouro")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<EnderecoListDto>> listarPorRua(@RequestParam String rua) {
        List<Endereco> enderecos = enderecoService.listarPorNomeLogradouro(rua);
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<EnderecoListDto> dtos = EnderecoMapper.toListagemDtos(enderecos);
        return ResponseEntity.status(200).body(dtos);
    }

    @Operation(
            summary = "Listar o endereço pelo tipo de logradouro",
            description = "Lista os endereços dado o tipo de logradouro especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços encontrados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoListDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum endereço encontrado", content = @Content)
    })
    @GetMapping("/tipo-logradouro")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<EnderecoListDto>> listarPorLogradouro(@RequestParam String logradouro) {
        List<Endereco> enderecos = enderecoService.listarPorLogradouro(logradouro);
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<EnderecoListDto> dtos = EnderecoMapper.toListagemDtos(enderecos);
        return ResponseEntity.status(200).body(dtos);
    }
    
}
