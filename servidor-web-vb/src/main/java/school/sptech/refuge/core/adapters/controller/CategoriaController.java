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
import school.sptech.refuge.core.adapters.dto.categoria.CategoriaAtualizacaoDto;
import school.sptech.refuge.core.adapters.dto.categoria.CategoriaListDto;
import school.sptech.refuge.core.adapters.dto.categoria.CategoriaMapper;
import school.sptech.refuge.core.adapters.dto.categoria.CategoriaRequestDto;
import school.sptech.refuge.core.adapters.dto.tipogenero.*;
import school.sptech.refuge.core.domain.categoria.valueobject.Categoria;
import school.sptech.refuge.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
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
        Categoria categoria = CategoriaMapper.toEntity(dto);
        Categoria categoriaCadastrada = categoriaService.cadastrar(categoria);
        CategoriaListDto dtoSalvo = CategoriaMapper.toListagemDto(categoriaCadastrada);
        return ResponseEntity.status(201).body(dtoSalvo);
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
        List<Categoria> categorias = categoriaService.listar();
        if (categorias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<CategoriaListDto> dtos = CategoriaMapper.toListagemDto(categorias);
        return ResponseEntity.status(200).body(dtos);
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
        Categoria categoria = categoriaService.buscarPorId(id);
        CategoriaListDto dto = CategoriaMapper.toListagemDto(categoria);
        return ResponseEntity.status(200).body(dto);
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
        Categoria categoria = CategoriaMapper.toEntity(dto, id);
        Categoria categotiaAtualizada = categoriaService.atualizar(categoria);
        CategoriaListDto dtoAtualizado = CategoriaMapper.toListagemDto(categotiaAtualizada);
        return ResponseEntity.status(200).body(dtoAtualizado);
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
        categoriaService.remover(id);
        return ResponseEntity.status(204).build();
    }
}
