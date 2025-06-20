package school.sptech.refuge.dto.beneficiario;

import school.sptech.refuge.dto.endereco.EnderecoListDto;
import school.sptech.refuge.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.entity.*;

import java.util.List;

public class BeneficiarioMapper {

    public static BeneficarioListDto toListagemDto(Beneficiario entity) {

        if (entity == null) {
            return null;
        }

        FuncionarioListDto funcionarioDto = new FuncionarioListDto(
                entity.getFuncionario().getId(),
                entity.getFuncionario().getNome(),
                entity.getFuncionario().getCpf(),
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

        TipoSexualidadeListDto tipoSexualidadeListDto = new TipoSexualidadeListDto(
                entity.getTipoSexualidade().getId(),
                entity.getTipoSexualidade().getNome(),
                entity.getTipoSexualidade().getDescricao()
        );

        String descricaoStatus = entity.getStatus() != null ? entity.getStatus().getDescricaoStatus() : "Status não definido";

        return new BeneficarioListDto(
                entity.getId(),
                entity.getNomeRegistro(),
                entity.getNomeSocial(),
                entity.getDtNasc(),
                entity.getCpf(),
                entity.getEstrangeiro(),
                entity.getRaca().getDescricaoRaca(),
                entity.getSexo().getDescricaoSexo(),
                entity.getNomeMae(),
                entity.getEgressoPrisional(),
                entity.getLocalDorme().getDescricaoLocal(),
                entity.getFotoPerfil(),
                entity.getSisa(),
                descricaoStatus,
                entity.getDataAtivacao(),
                entity.getObservacao(),
                funcionarioDto,
                enderecoListDto,
                tipoGeneroListDto,
                tipoSexualidadeListDto
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

    public static Beneficiario toEntity(BeneficiarioRequestDto dto) {

        if (dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getIdFuncionario());

        Endereco endereco = new Endereco();
        endereco.setId(dto.getIdEndereco());

        TipoGenero tipoGenero = new TipoGenero();
        tipoGenero.setId(dto.getIdTipoGenero());

        TipoSexualidade tipoSexualidade = new TipoSexualidade();
        tipoSexualidade.setId(dto.getIdTipoSexualidade());

        return new Beneficiario(
                null,
                dto.getNomeRegistro(),
                dto.getNomeSocial(),
                dto.getDtNasc(),
                dto.getCpf(),
                dto.getEstrangeiro(),
                RacaEnum.fromString(dto.getRaca()),
                SexoEnum.fromString(dto.getSexo()),
                dto.getNomeMae(),
                dto.getEgressoPrisional(),
                LocalEnum.fromString(dto.getLocalDorme()),
                dto.getFotoPerfil(),
                dto.getSisa(),
                StatusEnum.fromString(dto.getStatus()),
                dto.getData_ativacao(),
                dto.getObservacao(),
                funcionario,
                endereco,
                tipoGenero,
                tipoSexualidade
        );

    }

    public static Beneficiario toEntity(BeneficiarioAtualizacaoDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getIdFuncionario());

        Endereco endereco = new Endereco();
        endereco.setId(dto.getIdEndereco());

        TipoGenero tipoGenero = new TipoGenero();
        tipoGenero.setId(dto.getIdTipoGenero());

        TipoSexualidade tipoSexualidade = new TipoSexualidade();
        tipoSexualidade.setId(dto.getIdTipoSexualidade());

        return new Beneficiario(
                id,
                dto.getNomeRegistro(),
                dto.getNomeSocial(),
                dto.getDtNasc(),
                dto.getCpf(),
                dto.getEstrangeiro(),
                RacaEnum.fromString(dto.getRaca()),
                SexoEnum.fromString(dto.getSexo()),
                dto.getNomeMae(),
                dto.getEgressoPrisional(),
                LocalEnum.fromString(dto.getLocalDorme()),
                dto.getFotoPerfil(),
                dto.getSisa(),
                StatusEnum.fromString(dto.getStatus()),
                dto.getData_ativacao(),
                dto.getObservacao(),
                funcionario,
                endereco,
                tipoGenero,
                tipoSexualidade
        );
    }
}
