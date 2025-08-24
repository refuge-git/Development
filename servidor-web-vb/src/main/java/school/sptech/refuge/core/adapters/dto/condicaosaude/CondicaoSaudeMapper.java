package school.sptech.refuge.core.adapters.dto.condicaosaude;

import school.sptech.refuge.core.domain.beneficiario.valueobject.Beneficiario;
import school.sptech.refuge.core.domain.categoria.valueobject.Categoria;
import school.sptech.refuge.core.domain.condicaosaude.valueobject.CondicaoSaude;
import school.sptech.refuge.core.adapters.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.adapters.dto.categoria.CategoriaListDto;
import school.sptech.refuge.core.adapters.dto.endereco.EnderecoListDto;
import school.sptech.refuge.core.adapters.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.adapters.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.adapters.dto.tiposexualidade.TipoSexualidadeListDto;

import java.time.LocalDateTime;
import java.util.List;

public class CondicaoSaudeMapper {
    public static CondicaoSaudeListDto toListagemDto(school.sptech.refuge.core.domain.condicaosaude.valueobject.CondicaoSaude entity) {
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

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(request.getIdBeneficiario());

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
                beneficiario,
                categoria
        );
    }

    public static CondicaoSaude toEntity(CondicaoSaudeAtualizacaoDto dto, Integer id) {
        if (dto == null) return null;

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(dto.getIdBeneficiario());

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
                beneficiario,
                categoria
        );
    }
}
