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
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.core.application.dto.funcionario.*;
import school.sptech.refuge.core.application.usecase.funcionario.*;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioMapper;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final CriarFuncionarioUseCase criarFuncionarioUseCase;
    private final AtualizarFuncionarioUseCase atualizarFuncionarioUseCase;
    private final BuscarFuncionarioUseCase buscarFuncionarioUseCase;
    private final ListarTodosFuncionariosUseCase listarTodosFuncionariosUseCase;
    private final DeletarFuncionarioUseCase deletarFuncionarioUseCase;
    private final AutenticarFuncionarioUseCase autenticarFuncionarioUseCase;

    public FuncionarioController(CriarFuncionarioUseCase criarFuncionarioUseCase, AtualizarFuncionarioUseCase atualizarFuncionarioUseCase, BuscarFuncionarioUseCase buscarFuncionarioUseCase, ListarTodosFuncionariosUseCase listarTodosFuncionariosUseCase, DeletarFuncionarioUseCase deletarFuncionarioUseCase, AutenticarFuncionarioUseCase autenticarFuncionarioUseCase) {
        this.criarFuncionarioUseCase = criarFuncionarioUseCase;
        this.atualizarFuncionarioUseCase = atualizarFuncionarioUseCase;
        this.buscarFuncionarioUseCase = buscarFuncionarioUseCase;
        this.listarTodosFuncionariosUseCase = listarTodosFuncionariosUseCase;
        this.deletarFuncionarioUseCase = deletarFuncionarioUseCase;
        this.autenticarFuncionarioUseCase = autenticarFuncionarioUseCase;
    }

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
    public ResponseEntity<FuncionarioListDto> criar(@RequestBody @Valid FuncionarioRequestDto funcionarioRequestDto){
        FuncionarioListDto criado = criarFuncionarioUseCase.execute(funcionarioRequestDto);
        return ResponseEntity.status(201).body(criado);
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
        FuncionarioTokenDto dto = autenticarFuncionarioUseCase.autenticar(funcionarioLoginDto.getEmail(), funcionarioLoginDto.getSenha());
        return ResponseEntity.ok(dto);
    }


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

        List<FuncionarioListDto> funcionariosEncontrados = listarTodosFuncionariosUseCase.execute();

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
        FuncionarioListDto funcionario = buscarFuncionarioUseCase.execute(id);
        return ResponseEntity.status(200).body(funcionario);
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
        FuncionarioListDto dtoAtualizado = atualizarFuncionarioUseCase.execute(id, dto);
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
        deletarFuncionarioUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }

}
