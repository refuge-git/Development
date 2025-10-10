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
import school.sptech.refuge.core.application.dto.categoria.CategoriaAtualizacaoDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaListDto;
import school.sptech.refuge.core.application.usecase.categoria.*;
import school.sptech.refuge.infrastructure.bd.categoria.CategoriaMapper;
import school.sptech.refuge.core.application.dto.categoria.CategoriaRequestDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CriarCategoriaUseCase criarCategoriaUseCase;
    private final BuscarCategoriaUseCase buscarCategoriaUseCase;
    private final AtualizarCategoriaUseCase atualizarCategoriaUseCase;
    private final ListarTodasCategoriaUseCase listarTodasCategoriaUseCase;
    private final DeletarCategoriaUseCase deletarCategoriaUseCase;

    public CategoriaController(CriarCategoriaUseCase criarCategoriaUseCase, BuscarCategoriaUseCase buscarCategoriaUseCase, AtualizarCategoriaUseCase atualizarCategoriaUseCase, ListarTodasCategoriaUseCase listarTodasCategoriaUseCase, DeletarCategoriaUseCase deletarCategoriaUseCase) {
        this.criarCategoriaUseCase = criarCategoriaUseCase;
        this.buscarCategoriaUseCase = buscarCategoriaUseCase;
        this.atualizarCategoriaUseCase = atualizarCategoriaUseCase;
        this.listarTodasCategoriaUseCase = listarTodasCategoriaUseCase;
        this.deletarCategoriaUseCase = deletarCategoriaUseCase;
    }

    @Operation(
            summary = "Cadastrar uma categoria",
            description = "Cadastra uma categoria novo passado pelo body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoGeneroListDto.class)))
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CategoriaListDto> cadastrar(@Valid @RequestBody CategoriaRequestDto dto) {
        CategoriaListDto criado = criarCategoriaUseCase.execute(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @Operation(
            summary = "Listar categorias",
            description = "Lista todos as categorias cadastradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias encontradas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoGeneroListDto.class))),
            @ApiResponse(responseCode = "204", description = "Não há categorias cadastradas", content = @Content)
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<CategoriaListDto>> listar() {
        List<CategoriaListDto> categoriaListDtos = listarTodasCategoriaUseCase.execute();
        if(categoriaListDtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categoriaListDtos);
    }


    @Operation(
            summary = "Listar categorias por id",
            description = "Lista a categoria especificada dado o id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoGeneroListDto.class)))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CategoriaListDto> listarPorId(@PathVariable Integer id) {
        CategoriaListDto dto = buscarCategoriaUseCase.execute(id);
        return ResponseEntity.ok(dto);
    }


    @Operation(
            summary = "Atualizar categoria",
            description = "Atualiza a categoria dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoGeneroListDto.class)))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CategoriaListDto> atualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaAtualizacaoDto dto) {
        CategoriaListDto dtoAtualizado = atualizarCategoriaUseCase.execute(id, dto);
        return ResponseEntity.ok(dtoAtualizado);

    }


    @Operation(
            summary = "Excluir categoria",
            description = "Exclui a categoria dado o id especificado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria excluida com sucesso")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        deletarCategoriaUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
