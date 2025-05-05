package school.sptech.refuge.dto.beneficiario;

import school.sptech.refuge.dto.FuncionarioBeneficiarioListDto;
import school.sptech.refuge.dto.endereco.EnderecoListDto;
import school.sptech.refuge.dto.tipogenero.TipoGeneroListDto;
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

        EnderecoListDto enderecoListDto = new EnderecoListDto(
                entity.getEndereco().getId(),
                entity.getEndereco().getTipoLogradouro(),
                entity.getEndereco().getNomeLogradouro(),
                entity.getEndereco().getNumero(),
                entity.getEndereco().getComplemento(),
                entity.getEndereco().getBairro(),
                entity.getEndereco().getCep(),
                entity.getEndereco().getNomeLocalidade(),
                entity.getEndereco().getSiglaCidade()

        );

        TipoGeneroListDto tipoGeneroListDto = new TipoGeneroListDto(
                entity.getTipoGenero().getId(),
                entity.getTipoGenero().getNome(),
                entity.getTipoGenero().getDescricao()
        );

        return new BeneficarioListDto(
                entity.getId(),
                entity.getNomeRegistro(),
                entity.getNomeSocial(),
                entity.getDtNasc(),
                entity.getCpf(),
                entity.getRaca().getDescricaoRaca(),
                entity.getNomeMae(),
                entity.getFotoPerfil(),
                entity.getSisa(),
                entity.getStatusEnum().getDescricaoStatus(),
                entity.getDataAtivacao(),
                funcionarioDto,
                enderecoListDto,
                tipoGeneroListDto
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

    public static Beneficiario toEntity(BeneficiarioRequestDto dto, Funcionario funcionario, Endereco endereco, TipoGenero tipoGenero) {

        return new Beneficiario(
                null,
                dto.getNomeRegistro(),
                dto.getNomeSocial(),
                dto.getDtNasc(),
                dto.getCpf(),
                RacaEnum.fromString(dto.getRaca()),
                dto.getNomeMae(),
                dto.getFotoPerfil(),
                dto.getSisa(),
                StatusEnum.fromString(dto.getStatus()),
                dto.getData_ativacao(),
                funcionario,
                endereco,
                tipoGenero
        );
    }

    public static Beneficiario toEntity(BeneficiarioAtualizacaoDto dto, Integer id, Funcionario funcionario, TipoGenero tipoGenero, Endereco endereco) {
        if (dto == null) {
            return null;
        }

        return new Beneficiario(
                id,
                dto.getNomeRegistro(),
                dto.getNomeSocial(),
                dto.getDtNasc(),
                dto.getCpf(),
                dto.getRaca(),
                dto.getNomeMae(),
                dto.getFotoPerfil(),
                dto.getSisa(),
                dto.getStatusEnum(),
                dto.getData_ativacao(),
                funcionario,
                endereco,
                tipoGenero

        );
    }
}
