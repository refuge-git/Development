package school.sptech.refuge.core.adapters.dto.tiposexualidade;

import school.sptech.refuge.core.domain.tiposexualidade.valueobject.TipoSexualidade;

import java.util.List;

public class TipoSexualidadeMapper {

    public static TipoSexualidadeListDto toListagemDto(TipoSexualidade entity) {

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

    public static List<TipoSexualidadeListDto> toListagemDtos(List<TipoSexualidade> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(TipoSexualidadeMapper::toListagemDto)
                .toList();
    }

    public static TipoSexualidade toEntity(TipoSexualidadeRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return new TipoSexualidade(
                null,
                dto.getNome(),
                dto.getDescricao()
        );
    }

    public static TipoSexualidade toEntity(TipoSexualidadeRequestDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        return new TipoSexualidade(
                id,
                dto.getNome(),
                dto.getDescricao()
        );
    }
}
