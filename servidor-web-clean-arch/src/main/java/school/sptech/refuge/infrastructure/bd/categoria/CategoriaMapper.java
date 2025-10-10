package school.sptech.refuge.infrastructure.bd.categoria;

import school.sptech.refuge.core.application.dto.categoria.CategoriaAtualizacaoDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaListDto;
import school.sptech.refuge.core.application.dto.categoria.CategoriaRequestDto;
import school.sptech.refuge.core.domain.categoria.Categoria;

import java.util.List;
import java.util.Objects;

public class CategoriaMapper {

    public static CategoriaEntity ofDomain(Categoria categoria){
        if (Objects.isNull(categoria)) {
            return null;
        }

        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(categoria.getId());
        entity.setNome(categoria.getNome());

        return entity;
    }

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

    public static Categoria ofEntity(CategoriaEntity categoriaEntity){
        if(Objects.isNull(categoriaEntity)){
            return null;
        }

        Categoria dominio = new Categoria();
        dominio.setId(categoriaEntity.getId());
        dominio.setNome(categoriaEntity.getNome());

        return dominio;
    }

}
