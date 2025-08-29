package school.sptech.refuge.infrastructure.bd.tipogenero;

import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroRequestDto;

import java.util.List;

public class TipoGeneroMapper {

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
