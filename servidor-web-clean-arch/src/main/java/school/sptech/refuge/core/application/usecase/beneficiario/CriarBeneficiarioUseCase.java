package school.sptech.refuge.core.application.usecase.beneficiario;

import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.adapters.EnderecoGateway;
import school.sptech.refuge.core.adapters.FuncionarioGateway;
import school.sptech.refuge.core.adapters.TipoGeneroGateway;
import school.sptech.refuge.core.adapters.TipoSexualidadeGateway;

import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.application.exception.EnderecoNaoEncontradoException;
import school.sptech.refuge.core.application.exception.FuncionarioNaoEncontradaException;
import school.sptech.refuge.core.application.exception.TipoGeneroNaoEncontradoException;
import school.sptech.refuge.core.application.exception.TipoSexualidadeNaoEncontradoException;
import school.sptech.refuge.core.domain.beneficiario.*;
import school.sptech.refuge.core.domain.endereco.Endereco;
import school.sptech.refuge.core.domain.funcionario.Funcionario;
import school.sptech.refuge.core.domain.tipogenero.TipoGenero;
import school.sptech.refuge.core.domain.tiposexualidade.TipoSexualidade;
import school.sptech.refuge.infrastructure.bd.beneficiario.BeneficiarioMapper;

import java.time.LocalDateTime;

public class CriarBeneficiarioUseCase {

    private final BeneficiarioGateway beneficiarioGateway;
    private final FuncionarioGateway funcionarioGateway;
    private final EnderecoGateway enderecoGateway;
    private final TipoGeneroGateway tipoGeneroGateway;
    private final TipoSexualidadeGateway tipoSexualidadeGateway;

    public CriarBeneficiarioUseCase(BeneficiarioGateway beneficiarioGateway,
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

    public BeneficarioListDto execute(BeneficiarioRequestDto dto) {

        Funcionario funcionario = funcionarioGateway.buscarPorId(dto.getIdFuncionario())
                .orElseThrow(() -> new FuncionarioNaoEncontradaException("Funcionário não encontrado"));

        Endereco endereco = null;
        if (dto.getIdEndereco() != null) {
            endereco = enderecoGateway.findById(dto.getIdEndereco())
                    .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado"));
        }

        TipoGenero tipoGenero = tipoGeneroGateway.buscarPorId(dto.getIdTipoGenero())
                .orElseThrow(() -> new TipoGeneroNaoEncontradoException("Tipo de gênero não encontrado"));

        TipoSexualidade tipoSexualidade = tipoSexualidadeGateway.buscarPorId(dto.getIdTipoSexualidade())
                .orElseThrow(() -> new TipoSexualidadeNaoEncontradoException("Tipo de sexualidade não encontrado"));

        Beneficiario beneficiario = new Beneficiario(
                null,
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
                StatusEnum.ATIVO,
                LocalDateTime.now(),
                dto.getObservacao(),
                funcionario,
                endereco,
                tipoGenero,
                tipoSexualidade
        );

        return BeneficiarioMapper.fromDomain(beneficiarioGateway.salvar(beneficiario));
    }
}
