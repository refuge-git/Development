package school.sptech.refuge.infrastructure.bd.categoria;

import school.sptech.refuge.core.application.dto.categoria.CategoriaAtualizacaoDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaListDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaRequestDto;

import java.util.List;

public class CategoriaMapper {
    public static CategoriaListDto toListagemDto(CategoriaEntity categoriaEntity) {
        if (categoriaEntity == null) return null;

        return new CategoriaListDto(
                categoriaEntity.getId(),
                categoriaEntity.getNome()
        );

    }

    // Converte uma lista de entidades Categoria em uma lista de DTOs
    public static List<CategoriaListDto> toListagemDto(List<CategoriaEntity> categoriaEntities) {
        if (categoriaEntities == null) return null;

        return categoriaEntities.stream().map(CategoriaMapper::toListagemDto)
                .toList();
    }

    /* Converte um objeto que veio da requisição (CondicaoSaudeRequestDto) em um objeto de entidade (CondicaoSaudez) que pode ser salvo no banco. */
    public static CategoriaEntity toEntity(CategoriaRequestDto request) {
        if (request == null) return null;

        return new CategoriaEntity(
                null,
                request.getNome()
        );
    }

    public static CategoriaEntity toEntity(CategoriaAtualizacaoDto dto, Integer id) {

        if (dto == null) return null;

        return new CategoriaEntity(
                id,
                dto.getNome()
        );
    }

}
