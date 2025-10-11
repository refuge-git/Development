package school.sptech.refuge.infrastructure.bd.beneficiario;


import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.refuge.core.application.dto.endereco.EnderecoResponseDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioAtualizacaoDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.domain.beneficiario.*;
//import school.sptech.refuge.entity.*;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.infrastructure.bd.endereco.EnderecoMapper;
import school.sptech.refuge.infrastructure.bd.funcionario.FuncionarioMapper;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroEntity;
import school.sptech.refuge.infrastructure.bd.tipogenero.TipoGeneroMapper;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeEntity;
import school.sptech.refuge.infrastructure.bd.tiposexualidade.TipoSexualidadeMapper;
import school.sptech.refuge.infrastructure.config.bucketS3.S3UploadService;

import java.util.List;
import java.util.Objects;

public class BeneficiarioMapper {
    @Autowired
    S3UploadService s3UploadService;

    public static BeneficarioListDto fromDomain(Beneficiario beneficiario) {
        if (beneficiario == null) {
            return null;
        }

        return new BeneficarioListDto(
                beneficiario.getId(),
                beneficiario.getNomeRegistro(),
                beneficiario.getNomeSocial(),
                beneficiario.getDtNasc(),
                beneficiario.getCpf(),
                beneficiario.getEstrangeiro(),
                beneficiario.getRaca() != null ? beneficiario.getRaca().name() : null,
                beneficiario.getSexo() != null ? beneficiario.getSexo().name() : null,
                beneficiario.getNomeMae(),
                beneficiario.getEgressoPrisional(),
                beneficiario.getLocalDorme() != null ? beneficiario.getLocalDorme().name() : null,
                beneficiario.getFotoPerfil(),
                beneficiario.getSisa(),
                // beneficiario.getStatus().name(),
                beneficiario.getStatus() != null ? beneficiario.getStatus().name() : "ATIVO",
                beneficiario.getDataAtivacao(),
                beneficiario.getObservacao(),
                beneficiario.getFuncionario() != null ? new FuncionarioListDto(
                        beneficiario.getFuncionario().getId(),
                        beneficiario.getFuncionario().getNome(),
                        beneficiario.getFuncionario().getCpf(),
                        beneficiario.getFuncionario().getTelefone(),
                        beneficiario.getFuncionario().getEmail()
                ) : null,
                beneficiario.getEndereco() != null ? new EnderecoResponseDto(
                        beneficiario.getEndereco().getId(),
                        beneficiario.getEndereco().getTipoLogradouro(),
                        beneficiario.getEndereco().getNomeLogradouro(),
                        beneficiario.getEndereco().getNumero(),
                        beneficiario.getEndereco().getComplemento(),
                        beneficiario.getEndereco().getBairro(),
                        beneficiario.getEndereco().getCep(),
                        beneficiario.getEndereco().getNomeLocalidade()
                ) : null,
                beneficiario.getTipoGenero() != null ? new TipoGeneroListDto(
                        beneficiario.getTipoGenero().getId(),
                        beneficiario.getTipoGenero().getNome(),
                        beneficiario.getTipoGenero().getDescricao()
                ) : null,
                beneficiario.getTipoSexualidade() != null ? new TipoSexualidadeListDto(
                        beneficiario.getTipoSexualidade().getId(),
                        beneficiario.getTipoSexualidade().getNome(),
                        beneficiario.getTipoSexualidade().getDescricao()
                ) : null,
                beneficiario.getFotoPerfil()
        );
    }

    public static BeneficiarioEntity ofDomain(Beneficiario beneficiario) {
        if (Objects.isNull(beneficiario)) {
            return null;
        }

        BeneficiarioEntity entity = new BeneficiarioEntity();
        entity.setId(beneficiario.getId());
        entity.setNomeRegistro(beneficiario.getNomeRegistro());
        entity.setNomeSocial(beneficiario.getNomeSocial());
        entity.setDtNasc(beneficiario.getDtNasc());
        entity.setCpf(beneficiario.getCpf());
        entity.setEstrangeiro(beneficiario.getEstrangeiro());
        entity.setRaca(beneficiario.getRaca() != null ? beneficiario.getRaca() : null);
        entity.setSexo(beneficiario.getSexo() != null ? beneficiario.getSexo() : null);
        entity.setNomeMae(beneficiario.getNomeMae());
        entity.setEgressoPrisional(beneficiario.getEgressoPrisional());
        entity.setLocalDorme(beneficiario.getLocalDorme() != null ? beneficiario.getLocalDorme() : null);
        entity.setFotoPerfil(beneficiario.getFotoPerfil());
        entity.setSisa(beneficiario.getSisa());
        entity.setStatus(beneficiario.getStatus());
        entity.setDataAtivacao(beneficiario.getDataAtivacao());
        entity.setObservacao(beneficiario.getObservacao());


        entity.setFuncionarioEntity(FuncionarioMapper.ofDomain(beneficiario.getFuncionario()));
        entity.setEnderecoEntity(EnderecoMapper.ofDomain(beneficiario.getEndereco()));
        entity.setTipoGeneroEntity(TipoGeneroMapper.ofDomain(beneficiario.getTipoGenero()));
        entity.setTipoSexualidadeEntity(TipoSexualidadeMapper.ofDomain(beneficiario.getTipoSexualidade()));

        return entity;
    }

    public static Beneficiario ofEntity(BeneficiarioEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return new Beneficiario(
                entity.getId(),
                entity.getNomeRegistro(),
                entity.getNomeSocial(),
                entity.getDtNasc(),
                entity.getCpf(),
                entity.getEstrangeiro(),
                entity.getRaca() != null ? RacaEnum.valueOf(entity.getRaca().name()) : null,
                entity.getSexo() != null ? SexoEnum.valueOf(entity.getSexo().name()) : null,
                entity.getNomeMae(),
                entity.getEgressoPrisional(),
                entity.getLocalDorme() != null ? LocalEnum.valueOf(entity.getLocalDorme().name()) : null,
                entity.getFotoPerfil(),
                entity.getSisa(),
                entity.getStatus() != null ? StatusEnum.valueOf(entity.getStatus().name()) : null,
                entity.getDataAtivacao(),
                entity.getObservacao(),
                FuncionarioMapper.ofEntity(entity.getFuncionarioEntity()),
                EnderecoMapper.ofEntity(entity.getEnderecoEntity()),
                TipoGeneroMapper.ofEntity(entity.getTipoGeneroEntity()),
                TipoSexualidadeMapper.ofEntity(entity.getTipoSexualidadeEntity())
        );
    }

    /*public static BeneficarioListDto toListagemDto(BeneficiarioEntity entity) {

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

    public static List<BeneficarioListDto> toListagemDtos(List<BeneficiarioEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(BeneficiarioMapper::toListagemDto)
                .toList();
    }

    public static BeneficiarioEntity toEntity(BeneficiarioRequestDto dto) {

        if (dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getIdFuncionario());

        Endereco endereco = new Endereco();
        endereco.setId(dto.getIdEndereco());

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity();
        tipoGeneroEntity.setId(dto.getIdTipoGenero());

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity();
        tipoSexualidadeEntity.setId(dto.getIdTipoSexualidade());

        return new BeneficiarioEntity(
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
                tipoGeneroEntity,
                tipoSexualidadeEntity
        );

    }

    public static BeneficiarioEntity toEntity(BeneficiarioAtualizacaoDto dto, Integer id) {
        if (dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getIdFuncionario());

        Endereco endereco = new Endereco();
        endereco.setId(dto.getIdEndereco());

        TipoGeneroEntity tipoGeneroEntity = new TipoGeneroEntity();
        tipoGeneroEntity.setId(dto.getIdTipoGenero());

        TipoSexualidadeEntity tipoSexualidadeEntity = new TipoSexualidadeEntity();
        tipoSexualidadeEntity.setId(dto.getIdTipoSexualidade());

        return new BeneficiarioEntity(
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
                tipoGeneroEntity,
                tipoSexualidadeEntity
        );
    }*/
}
