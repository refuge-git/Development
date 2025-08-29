package school.sptech.refuge.antes.dto.condicaosaude;

import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.antes.dto.categoria.CategoriaListDto;
import school.sptech.refuge.antes.dto.endereco.EnderecoListDto;
import school.sptech.refuge.antes.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioEntity;
import school.sptech.refuge.antes.entity.Categoria;
import school.sptech.refuge.antes.entity.CondicaoSaude;
import school.sptech.refuge.entity.*;

import java.time.LocalDateTime;
import java.util.List;

public class CondicaoSaudeMapper {
    public static CondicaoSaudeListDto toListagemDto(CondicaoSaude entity) {
        if (entity == null) return null;

        CategoriaListDto categoriaListDto = new CategoriaListDto(
                entity.getCategoria().getId(),
                entity.getCategoria().getNome()
        );

        FuncionarioListDto funcionarioDto = new FuncionarioListDto(
                entity.getBeneficiario().getFuncionario().getId(),
                entity.getBeneficiario().getFuncionario().getNome(),
                entity.getBeneficiario().getFuncionario().getCpf(),
                entity.getBeneficiario().getFuncionario().getEmail(),
                entity.getBeneficiario().getFuncionario().getTelefone()
        );

        EnderecoListDto enderecoListDto = new EnderecoListDto(
                entity.getBeneficiario().getEndereco().getId(),
                entity.getBeneficiario().getEndereco().getTipoLogradouro(),
                entity.getBeneficiario().getEndereco().getNomeLogradouro(),
                entity.getBeneficiario().getEndereco().getNumero(),
                entity.getBeneficiario().getEndereco().getComplemento(),
                entity.getBeneficiario().getEndereco().getBairro(),
                entity.getBeneficiario().getEndereco().getCep(),
                entity.getBeneficiario().getEndereco().getNomeLocalidade(),
                entity.getBeneficiario().getEndereco().getSiglaCidade()

        );

        TipoGeneroListDto tipoGeneroListDto = new TipoGeneroListDto(
                entity.getBeneficiario().getTipoGenero().getId(),
                entity.getBeneficiario().getTipoGenero().getNome(),
                entity.getBeneficiario().getTipoGenero().getDescricao()
        );

        TipoSexualidadeListDto tipoSexualidadeListDto = new TipoSexualidadeListDto(
                entity.getBeneficiario().getTipoSexualidade().getId(),
                entity.getBeneficiario().getTipoSexualidade().getNome(),
                entity.getBeneficiario().getTipoSexualidade().getDescricao()
        );

        String descricaoStatus = entity.getBeneficiario().getStatus() != null ? entity.getBeneficiario().getStatus().getDescricaoStatus() : "Status não definido";

        BeneficarioListDto beneficarioListDto = new BeneficarioListDto(
                entity.getBeneficiario().getId(),
                entity.getBeneficiario().getNomeRegistro(),
                entity.getBeneficiario().getNomeSocial(),
                entity.getBeneficiario().getDtNasc(),
                entity.getBeneficiario().getCpf(),
                entity.getBeneficiario().getEstrangeiro(),
                entity.getBeneficiario().getRaca().getDescricaoRaca(),
                entity.getBeneficiario().getSexo().getDescricaoSexo(),
                entity.getBeneficiario().getNomeMae(),
                entity.getBeneficiario().getEgressoPrisional(),
                entity.getBeneficiario().getLocalDorme().getDescricaoLocal(),
                entity.getBeneficiario().getFotoPerfil(),
                entity.getBeneficiario().getSisa(),
                descricaoStatus,
                entity.getBeneficiario().getDataAtivacao(),
                entity.getBeneficiario().getObservacao(),
                funcionarioDto,
                enderecoListDto,
                tipoGeneroListDto,
                tipoSexualidadeListDto
        );

        return new CondicaoSaudeListDto(
                entity.getId(),
                entity.getDiagnostico(),
                entity.getDescricao(),
                entity.getDataRegistro(),
                entity.getDataAtualizacao(),
                entity.getTratamento(),
                entity.getObservacoes(),
                beneficarioListDto,
                categoriaListDto
        );
    }

    // Converte uma lista de entidades CondicaoSaude em uma lista de DTOs
    public static List<CondicaoSaudeListDto> toListagemDtos(List<CondicaoSaude> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(CondicaoSaudeMapper::toListagemDto)
                .toList();
    }

    /* Converte um objeto que veio da requisição (CondicaoSaudeRequestDto) em um objeto de entidade (CondicaoSaudez) que pode ser salvo no banco. */
    public static CondicaoSaude toEntity(CondicaoSaudeRequestDto request) {
        if (request == null) return null;

        BeneficiarioEntity beneficiarioEntity = new BeneficiarioEntity();
        beneficiarioEntity.setId(request.getIdBeneficiario());

        Categoria categoria = new Categoria();
        categoria.setId(request.getIdCategoria());

        LocalDateTime agora = LocalDateTime.now();

        return new CondicaoSaude(
                null,
                request.getDiagnostico(),
                request.getDescricao(),
                agora,
                agora,
                request.getTratamento(),
                request.getObservacoes(),
                beneficiarioEntity,
                categoria
        );
    }

    public static CondicaoSaude toEntity(CondicaoSaudeAtualizacaoDto dto, Integer id) {
        if (dto == null) return null;

        BeneficiarioEntity beneficiarioEntity = new BeneficiarioEntity();
        beneficiarioEntity.setId(dto.getIdBeneficiario());

        Categoria categoria = new Categoria();
        categoria.setId(dto.getIdCategoria());

        return new CondicaoSaude(
                id,
                dto.getDiagnostico(),
                dto.getDescricao(),
                null,
                LocalDateTime.now(),
                dto.getTratamento(),
                dto.getObservacoes(),
                beneficiarioEntity,
                categoria
        );
    }
}
