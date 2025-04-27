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
                entity.getCep(),
                entity.getRua(),
                entity.getBairro(),
                entity.getLogradouro(),
                entity.getNumero()
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

    public static Endereco toEntity(EnderecoRequestDto dto, Beneficiario beneficiario) {
        if (dto == null || beneficiario == null) {
            return null;
        }

        return new Endereco(
                null,
                dto.getCep(),
                dto.getRua(),
                dto.getBairro(),
                dto.getLogradouro(),
                dto.getNumero(),
                beneficiario

        );
    }

    public static Endereco toEntity(EnderecoAtualizacaoDto dto, Integer id, Beneficiario beneficiario) {
        if (dto == null) {
            return null;
        }

        return new Endereco(
                id,
                dto.getCep(),
                dto.getRua(),
                dto.getBairro(),
                dto.getLogradouro(),
                dto.getNumero(),
                null // Aqui você passa o beneficiário associado
        );
    }


}
