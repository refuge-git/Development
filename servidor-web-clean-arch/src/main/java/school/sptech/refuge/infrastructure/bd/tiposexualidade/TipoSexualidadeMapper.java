package school.sptech.refuge.infrastructure.bd.tiposexualidade;

import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeRequestDto;

import java.util.List;

public class TipoSexualidadeMapper {

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
