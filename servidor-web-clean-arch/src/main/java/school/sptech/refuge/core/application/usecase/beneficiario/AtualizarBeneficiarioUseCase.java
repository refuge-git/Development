package school.sptech.refuge.core.application.usecase.beneficiario;

import school.sptech.refuge.core.adapters.*;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioAtualizacaoDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.core.application.exception.*;
import school.sptech.refuge.core.domain.beneficiario.*;
import school.sptech.refuge.core.domain.endereco.Endereco;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;

public class AtualizarBeneficiarioUseCase {

    private final BeneficiarioGateway beneficiarioGateway;
    private final FuncionarioGateway funcionarioGateway;
    private final EnderecoGateway enderecoGateway;
    private final TipoGeneroGateway tipoGeneroGateway;
    private final TipoSexualidadeGateway tipoSexualidadeGateway;

    public AtualizarBeneficiarioUseCase(BeneficiarioGateway beneficiarioGateway,
                                        FuncionarioGateway funcionarioGateway,
                                        EnderecoGateway enderecoGateway,
                                        TipoGeneroGateway tipoGeneroGateway,
                                        TipoSexualidadeGateway tipoSexualidadeGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
        this.funcionarioGateway = funcionarioGateway;
        this.enderecoGateway = enderecoGateway;
        this.tipoGeneroGateway = tipoGeneroGateway;
        this.tipoSexualidadeGateway = tipoSexualidadeGateway;
    }

    public BeneficarioListDto execute(Integer id, BeneficiarioAtualizacaoDto dto) {
        if (!beneficiarioGateway.existePorId(id)) {
            throw new BeneficiarioNaoEncontradaException("Beneficiário não encontrado para atualização");
        }

        Funcionario funcionario = funcionarioGateway.buscarPorId(dto.getIdFuncionario())
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado"));

        Endereco endereco = enderecoGateway.findById(dto.getIdEndereco())
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado"));

        TipoGenero tipoGenero = tipoGeneroGateway.buscarPorId(dto.getIdTipoGenero())
                .orElseThrow(() -> new TipoGeneroNaoEncontradoException("Tipo de gênero não encontrado"));

        TipoSexualidade tipoSexualidade = tipoSexualidadeGateway.buscarPorId(dto.getIdTipoSexualidade())
                .orElseThrow(() -> new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade não encontrado"));

        Beneficiario beneficiarioAtualizado = new Beneficiario(
                id,
                dto.getNomeRegistro(),
                dto.getNomeSocial(),
                dto.getDtNasc(),
                dto.getCpf(),
                dto.getEstrangeiro(),
                RacaEnum.valueOf(dto.getRaca().toUpperCase()),
                SexoEnum.valueOf(dto.getSexo().toUpperCase()),
                dto.getNomeMae(),
                dto.getEgressoPrisional(),
                LocalEnum.valueOf(dto.getLocalDorme().toUpperCase()),
                dto.getFotoPerfil(),
                dto.getSisa(),
                dto.getStatus() != null ? StatusEnum.valueOf(dto.getStatus().toUpperCase()) : StatusEnum.ATIVO,
                dto.getData_ativacao(),
                dto.getObservacao(),
                funcionario,
                endereco,
                tipoGenero,
                tipoSexualidade
        );

        return BeneficiarioMapper.fromDomain(beneficiarioGateway.atualizar(id, beneficiarioAtualizado));
    }
}
