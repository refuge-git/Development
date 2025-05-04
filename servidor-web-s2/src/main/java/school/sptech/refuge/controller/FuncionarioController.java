package school.sptech.refuge.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.refuge.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.dto.funcionario.*;
import school.sptech.refuge.entity.Funcionario;
import school.sptech.refuge.repository.FuncionarioRepository;
import school.sptech.refuge.service.FuncionarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

//    @PostMapping
//    public ResponseEntity<FuncionarioListDto> cadastrar(@Valid @RequestBody FuncionarioRequestDto dto) {
//        Funcionario funcionario = FuncionarioMapper.toEntity(dto);
//        Funcionario funcionarioCadastrado = funcionarioService.cadastrar(funcionario);
//        FuncionarioListDto dtoSalvo = FuncionarioMapper.toListagemDto(funcionarioCadastrado);
//        return ResponseEntity.status(201).body(dtoSalvo);
//    }

    @Operation(
            summary = "Cadastro de funcionário",
            description = "Recebe os dados do funcionário pelo body e o transforma em entidade posteriormente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioRequestDto.class)))
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid FuncionarioRequestDto funcionarioRequestDto){
        final Funcionario novoFuncionario = FuncionarioMapper.of(funcionarioRequestDto);
        this.funcionarioService.criar(novoFuncionario);
        return ResponseEntity.status(201).build();
    }


    @Operation(
            summary = "Login do funcionário",
            description = "Autentica o funcionário a partir do email e senha"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioTokenDto.class)))
    })
    @PostMapping("/login")
    public  ResponseEntity<FuncionarioTokenDto> login (@RequestBody FuncionarioLoginDto funcionarioLoginDto){

        final Funcionario funcionario = FuncionarioMapper.of(funcionarioLoginDto);
        FuncionarioTokenDto funcionarioTokenDto = this.funcionarioService.autenticar(funcionario);

        return ResponseEntity.status(200).body(funcionarioTokenDto);
    }

//    @GetMapping
//    public ResponseEntity<List<FuncionarioListDto>> listar() {
//
//        List<Funcionario> funcionarios = funcionarioService.listar();
//
//        if (funcionarios.isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }
//
//        List<FuncionarioListDto> dtos = FuncionarioMapper.toListagemDtos(funcionarios);
//        return ResponseEntity.status(200).body(dtos);
//    }

    @Operation(
            summary = "Listagem de funcionários",
            description = "Lista todos os funcionários que estão cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioTokenDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum funcionário encontrado", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<FuncionarioListDto>> listarTodos(){

        List<FuncionarioListDto> funcionariosEncontrados = this.funcionarioService.listarTodos();

        if(funcionariosEncontrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(funcionariosEncontrados);
    }

    @Operation(
            summary = "Listar funcionário por id",
            description = "Lista o funcionário especificado dado o id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioTokenDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FuncionarioListDto> listarPorId(@PathVariable Integer id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        FuncionarioListDto dto = FuncionarioMapper.toListagemDto(funcionario);
        return ResponseEntity.status(200).body(dto);
    }


    @Operation(
            summary = "Atualiza o funcionário",
            description = "Atualiza o funcionário pelo id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioTokenDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FuncionarioListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody FuncionarioAtualizacaoDto dto) {
        Funcionario funcionario = FuncionarioMapper.toEntity(dto, id);
        Funcionario funcionarioAtualizado = funcionarioService.atualizar(funcionario);
        FuncionarioListDto dtoAtualizado = FuncionarioMapper.toListagemDto(funcionarioAtualizado);
        return ResponseEntity.status(200).body(dtoAtualizado);
    }


    @Operation(
            summary = "Deleta funcionário",
            description = "Deleta o funcionário pelo id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        funcionarioService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }

}
