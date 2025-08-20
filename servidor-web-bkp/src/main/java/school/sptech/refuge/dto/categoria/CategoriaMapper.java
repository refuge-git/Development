package school.sptech.refuge.dto.categoria;

import school.sptech.refuge.entity.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapper {
    public static CategoriaListDto toListagemDto(Categoria categoria) {
        if (categoria == null) return null;

        return new CategoriaListDto(
                categoria.getId(),
                categoria.getNome()
        );

    }

    // Converte uma lista de entidades Categoria em uma lista de DTOs
    public static List<CategoriaListDto> toListagemDto(List<Categoria> categorias) {
        if (categorias == null) return null;

        return categorias.stream().map(CategoriaMapper::toListagemDto)
                .toList();
    }

    /* Converte um objeto que veio da requisição (CondicaoSaudeRequestDto) em um objeto de entidade (CondicaoSaudez) que pode ser salvo no banco. */
    public static Categoria toEntity(CategoriaRequestDto request) {
        if (request == null) return null;

        return new Categoria(
                null,
                request.getNome()
        );
    }

    public static Categoria toEntity(CategoriaAtualizacaoDto dto, Integer id) {

        if (dto == null) return null;

        return new Categoria(
                id,
                dto.getNome()
        );
    }

}
