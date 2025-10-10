package school.sptech.refuge.infrastructure.bd.tipogenero;

import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroRequestDto;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;

import java.util.List;
import java.util.Objects;

public class TipoGeneroMapper {

    public static TipoGeneroEntity ofDomain(TipoGenero tipoGenero) {
        if (Objects.isNull(tipoGenero)) {
            return null;
        }

        TipoGeneroEntity entity = new TipoGeneroEntity();
        entity.setId(tipoGenero.getId());
        entity.setNome(tipoGenero.getNome());
        entity.setDescricao(tipoGenero.getDescricao());

        return entity;
    }

    public static TipoGenero ofEntity(TipoGeneroEntity tipoGeneroEntity) {
        if (Objects.isNull(tipoGeneroEntity)) {
            return null;
        }

        TipoGenero dominio = new TipoGenero();
        dominio.setId(tipoGeneroEntity.getId());
        dominio.setNome(tipoGeneroEntity.getNome());
        dominio.setDescricao(tipoGeneroEntity.getDescricao());

        return dominio;
    }

    public static TipoGenero toDomain(TipoGeneroRequestDto dto) {
        TipoGenero tipoGenero = new TipoGenero();
        tipoGenero.setNome(dto.getNome());
        tipoGenero.setDescricao(dto.getDescricao());
        return tipoGenero;
    }

    public static TipoGeneroListDto toDto(TipoGenero domain) {
        return new TipoGeneroListDto(
                domain.getId(),
                domain.getNome(),
                domain.getDescricao()
        );
    }

    public static TipoGeneroListDto toListagemDto(TipoGeneroEntity entity) {

        if (entity == null) {
            return null;
        }

        // Retornando instância diretamente
        return new TipoGeneroListDto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao()
        );

    }

    public static List<TipoGeneroListDto> toListagemDtos(List<TipoGeneroEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(TipoGeneroMapper::toListagemDto)
                .toList();
    }

    public static TipoGeneroEntity toEntity(TipoGeneroRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return new TipoGeneroEntity(
                null,
                dto.getNome(),
                dto.getDescricao()
        );
    }

    public static TipoGeneroEntity toEntity(TipoGeneroRequestDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        return new TipoGeneroEntity(
                id,
                dto.getNome(),
                dto.getDescricao()
        );
    }
}
