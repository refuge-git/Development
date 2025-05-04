package school.sptech.refuge.dto.endereco;

import school.sptech.refuge.entity.*;
import school.sptech.refuge.repository.BeneficiarioRepository;

import java.util.List;

public class EnderecoMapper {

    public static EnderecoListDto toListagemDto(Endereco entity) {

        if (entity == null) {
            return null;
        }

        return new EnderecoListDto(
                entity.getId(),
                entity.getTipoLogradouro(),
                entity.getNomeLogradouro(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getBairro(),
                entity.getCep(),
                entity.getNomeLocalidade(),
                entity.getSiglaCidade()
        );
    }

    public static List<EnderecoListDto> toListagemDtos(List<Endereco> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(EnderecoMapper::toListagemDto)
                .toList();
    }


    // SOBRE-CARGA DE MÉTODO
    public static Endereco toEntity(EnderecoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return new Endereco(
                null,
                dto.getTipoLogradouro(),
                dto.getNomeLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCep(),
                dto.getNomeLocalidade(),
                dto.getSiglaCidade()
        );
    }

    public static Endereco toEntity(EnderecoAtualizacaoDto dto) {
        if (dto == null) {
            return null;
        }

        return new Endereco(
                null,
                dto.getTipoLogradouro(),
                dto.getNomeLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCep(),
                dto.getNomeLocalidade(),
                dto.getSiglaCidade()
        );
    }

    public static Endereco toEntity(EnderecoAtualizacaoDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        return new Endereco(
                id,
                dto.getTipoLogradouro(),
                dto.getNomeLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCep(),
                dto.getNomeLocalidade(),
                dto.getSiglaCidade()
        );
    }


}
