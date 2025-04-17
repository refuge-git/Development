package school.sptech.refuge.dto.beneficiario;

import school.sptech.refuge.dto.FuncionarioBeneficiarioListDto;
import school.sptech.refuge.entity.*;

import java.util.List;

public class BeneficiarioMapper {

    public static BeneficarioListDto toListagemDto(Beneficiario entity) {

        if (entity == null) {
            return null;
        }

        FuncionarioBeneficiarioListDto funcionarioDto = new FuncionarioBeneficiarioListDto(
                entity.getFuncionario().getId(),
                entity.getFuncionario().getNome(),
                entity.getFuncionario().getEmail(),
                entity.getFuncionario().getTelefone()
        );

        return new BeneficarioListDto(
                entity.getId(),
                entity.getNome(),
                entity.getDtNasc(),
                entity.getCpf(),
                entity.getGenero().getDescricaoGenero(),
                entity.getRaca().getDescricaoRaca(),
                entity.getNomeMae(),
                entity.getFotoPerfil(),
                entity.getSisa(),
                entity.getStatusEnum().getDescricaoStatus(),
                entity.getData_ativacao(),
                funcionarioDto
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

    public static Beneficiario toEntity(BeneficiarioRequestDto dto, Funcionario funcionario) {
        if (dto == null) {
            return null;
        }

        return new Beneficiario(
                null,
                dto.getNome(),
                dto.getDtNasc(),
                dto.getCpf(),
                GeneroEnum.fromString(dto.getGenero()),
                RacaEnum.fromString(dto.getRaca()),
                dto.getNomeMae(),
                dto.getFotoPerfil(),
                dto.getSisa(),
                StatusEnum.fromString(dto.getStatus()),
                dto.getData_ativacao(),
                funcionario
        );
    }

    public static Beneficiario toEntity(BeneficiarioAtualizacaoDto dto, Integer id, Funcionario funcionario) {
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
                funcionario
        );
    }
}
