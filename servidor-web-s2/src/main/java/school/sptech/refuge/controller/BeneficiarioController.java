package school.sptech.refuge.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.dto.beneficiario.BeneficiarioAtualizacaoDto;
import school.sptech.refuge.dto.beneficiario.BeneficiarioMapper;
import school.sptech.refuge.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.dto.funcionario.FuncionarioAtualizacaoDto;
import school.sptech.refuge.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.dto.funcionario.FuncionarioMapper;
import school.sptech.refuge.dto.funcionario.FuncionarioRequestDto;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.entity.GeneroEnum;
import school.sptech.refuge.repository.BeneficiarioRepository;
import school.sptech.refuge.service.BeneficiarioService;
import school.sptech.refuge.service.FuncionarioService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beneficiarios") // URI do servidor
public class BeneficiarioController {


    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @Operation(
            summary = "Cadastro de usuário",
            description = "Recebe os dados do usuário pelo body e o transforma em entidade posteriormente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados de usuário inválidos ou ausente", content = @Content)
    })
    @PostMapping
    public ResponseEntity<BeneficarioListDto> cadastrar(@Valid @RequestBody BeneficiarioRequestDto dto) {
        Beneficiario beneficiario = BeneficiarioMapper.toEntity(dto, dto.getFuncionario());
        Beneficiario beneficiarioCadastrado = beneficiarioService.cadastrar(beneficiario);
        BeneficarioListDto dtoSalvo = BeneficiarioMapper.toListagemDto(beneficiarioCadastrado);
        return ResponseEntity.status(201).body(dtoSalvo);
    }

    @Operation(
            summary = "Listar usuários",
            description = "Retorna todos os usuários cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação bem-sucedida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficarioListDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há usuários cadastrados", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<BeneficarioListDto>> listar() {
        List<Beneficiario> beneficiarios = beneficiarioService.listar();
        if (beneficiarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<BeneficarioListDto> dtos = BeneficiarioMapper.toListagemDtos(beneficiarios);
        return ResponseEntity.status(200).body(dtos);
    }


    @Operation(
            summary = "Buscar usuário por id",
            description = "Retorna o usuário completo, dado o id passado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficarioListDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BeneficarioListDto> listarPorId(@PathVariable Integer id) {
        Beneficiario beneficiario = beneficiarioService.buscarPorId(id);
        BeneficarioListDto dto = BeneficiarioMapper.toListagemDto(beneficiario);
        return ResponseEntity.status(200).body(dto);
    }


    @Operation(
            summary = "Atualização de usuário",
            description = "Atualiza os dados do usuário pelo id passado e os dados presentes no body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficarioListDto.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<BeneficarioListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody BeneficiarioAtualizacaoDto dto) {
        Beneficiario beneficiario = BeneficiarioMapper.toEntity(dto, id, dto.getFuncionario());
        Beneficiario beneficiarioAtualizado = beneficiarioService.atualizar(beneficiario);
        BeneficarioListDto dtoAtualizado = BeneficiarioMapper.toListagemDto(beneficiarioAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }
    

    @GetMapping("/genero")
    public ResponseEntity<List<BeneficarioListDto>> listarPorGenero(@RequestParam String genero) {
        List<Beneficiario> beneficiario = beneficiarioService.listarPorGenero(genero);
        if (beneficiario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiario);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/raca")
    public ResponseEntity<List<BeneficarioListDto>> listarPorRaca(@RequestParam String raca) {
        List<Beneficiario> beneficiario = beneficiarioService.listarPorRaca(raca);
        if (beneficiario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiario);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<BeneficarioListDto>> listarContendoNome(@RequestParam String nome) {
        List<Beneficiario> beneficiario = beneficiarioService.listarNome(nome);
        if (beneficiario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BeneficarioListDto> dto = BeneficiarioMapper.toListagemDtos(beneficiario);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        beneficiarioService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }

}
