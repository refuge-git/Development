package school.sptech.refuge.dto.beneficiario;

import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.Endereco;
import school.sptech.refuge.entity.Funcionario;

import java.util.List;

public class BeneficiarioMapper {

    public static BeneficarioListDto toListagemDto(Beneficiario entity) {

        if (entity == null) {
            return null;
        }

        return new BeneficarioListDto(
                entity.getId(),
                entity.getNome(),
                entity.getDtNasc(),
                entity.getCpf(),
                entity.getGenero(),
                entity.getRaca(),
                entity.getNomeMae(),
                entity.getFotoPerfil(),
                entity.getSisa(),
                entity.getStatusEnum(),
                entity.getData_ativacao(),
                entity.getFuncionario().getNome()
        );
    }

    public static List<BeneficarioListDto> toListagemDtos(List<Beneficiario> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(BeneficiarioMapper::toListagemDto)
                .toList();
    }

    public static Beneficiario toEntity(BeneficiarioRequestDto dto, Endereco endereco, Funcionario funcionario) {
        if (dto == null) {
            return null;
        }

        return new Beneficiario(
                null,
                dto.getNome(),
                dto.getDtNasc(),
                dto.getCpf(),
                dto.getGenero(),
                dto.getRaca(),
                dto.getNomeMae(),
                dto.getFotoPerfil(),
                dto.getSisa(),
                dto.getStatusEnum(),
                dto.getData_ativacao(),
                endereco,
                funcionario
        );
    }

    public static Beneficiario toEntity(BeneficiarioAtualizacaoDto dto, Integer id, Endereco endereco, Funcionario funcionario) {
        if (dto == null) {
            return null;
        }

        return new Beneficiario(
                id,
                dto.getNome(),
                dto.getDtNasc(),
                dto.getCpf(),
                dto.getGenero(),
                dto.getRaca(),
                dto.getNomeMae(),
                dto.getFotoPerfil(),
                dto.getSisa(),
                dto.getStatusEnum(),
                dto.getData_ativacao(),
                endereco,
                funcionario
        );
    }
}
