package school.sptech.refuge.infrastructure.bd.tiposexualidade;

import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeRequestDto;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroEntity;

import java.util.List;
import java.util.Objects;

public class TipoSexualidadeMapper {

    public static TipoSexualidadeEntity ofDomain(TipoSexualidade tipoSexualidade) {
        if (Objects.isNull(tipoSexualidade)) {
            return null;
        }

        TipoSexualidadeEntity entity = new TipoSexualidadeEntity();
        entity.setId(tipoSexualidade.getId());
        entity.setNome(tipoSexualidade.getNome());
        entity.setDescricao(tipoSexualidade.getDescricao());

        return entity;
    }

    public static TipoSexualidade ofEntity(TipoSexualidadeEntity tipoSexualidadeEntity) {
        if (Objects.isNull(tipoSexualidadeEntity)) {
            return null;
        }

        TipoSexualidade dominio = new TipoSexualidade();
        dominio.setId(tipoSexualidadeEntity.getId());
        dominio.setNome(tipoSexualidadeEntity.getNome());
        dominio.setDescricao(tipoSexualidadeEntity.getDescricao());

        return dominio;
    }

    public static TipoSexualidadeListDto toListagemDto(TipoSexualidadeEntity entity) {

        if (entity == null) {
            return null;
        }

        // Retornando instância diretamente
        return new TipoSexualidadeListDto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao()
        );

    }

    public static List<TipoSexualidadeListDto> toListagemDtos(List<TipoSexualidadeEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(TipoSexualidadeMapper::toListagemDto)
                .toList();
    }

    public static TipoSexualidadeEntity toEntity(TipoSexualidadeRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return new TipoSexualidadeEntity(
                null,
                dto.getNome(),
                dto.getDescricao()
        );
    }

    public static TipoSexualidadeEntity toEntity(TipoSexualidadeRequestDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        return new TipoSexualidadeEntity(
                id,
                dto.getNome(),
                dto.getDescricao()
        );
    }
}
