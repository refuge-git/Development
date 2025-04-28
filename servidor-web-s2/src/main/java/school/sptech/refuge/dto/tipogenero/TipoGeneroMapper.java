package school.sptech.refuge.dto.tipogenero;

import school.sptech.refuge.entity.TipoGenero;

import java.util.List;

public class TipoGeneroMapper {

    public static TipoGeneroListDto toListagemDto(TipoGenero entity) {

        if (entity == null) {
            return null;
        }

        // Retornando instância diretamente
        return new TipoGeneroListDto(
                entity.getDescricao(),
                entity.getId(),
                entity.getNome()
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
                dto.getDescricao(),
                null,
                dto.getNome()
        );
    }

    public static TipoGenero toEntity(TipoGeneroRequestDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        return new TipoGenero(
                dto.getDescricao(),
                id,
                dto.getNome()
        );
    }
}
