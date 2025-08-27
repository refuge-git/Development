package school.sptech.refuge.core.application.dtos.tipogenero;

import school.sptech.refuge.core.domain.tipogenero.valueobject.TipoGenero;

import java.util.List;

public class TipoGeneroMapper {

    public static TipoGeneroListDto toListagemDto(TipoGenero entity) {

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

    public static List<TipoGeneroListDto> toListagemDtos(List<TipoGenero> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(TipoGeneroMapper::toListagemDto)
                .toList();
    }

    public static TipoGenero toEntity(TipoGeneroRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return new TipoGenero(
                null,
                dto.getNome(),
                dto.getDescricao()
        );
    }

    public static TipoGenero toEntity(TipoGeneroRequestDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        return new TipoGenero(
                id,
                dto.getNome(),
                dto.getDescricao()
        );
    }
}
