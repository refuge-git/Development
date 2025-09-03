package school.sptech.refuge.core.application.usecase.beneficiario;


import school.sptech.refuge.antes.dto.endereco.EnderecoListDto;
import school.sptech.refuge.antes.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.core.adapters.BeneficiarioGateway;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.core.application.dto.beneficiario.BeneficiarioRequestDto;
import school.sptech.refuge.core.application.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.core.application.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.core.domain.beneficiario.Beneficiario;
import school.sptech.refuge.core.domain.beneficiario.LocalEnum;
import school.sptech.refuge.core.domain.beneficiario.RacaEnum;
import school.sptech.refuge.core.domain.beneficiario.SexoEnum;

public class CriarBeneficiarioUseCase {

    private final BeneficiarioGateway beneficiarioGateway;

    public CriarBeneficiarioUseCase(BeneficiarioGateway beneficiarioGateway) {
        this.beneficiarioGateway = beneficiarioGateway;
    }

    public BeneficarioListDto execute(BeneficiarioRequestDto beneficiarioRequestDto) {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNomeRegistro(beneficiarioRequestDto.getNomeRegistro());
        beneficiario.setNomeSocial(beneficiarioRequestDto.getNomeSocial());
        beneficiario.setDtNasc(beneficiarioRequestDto.getDtNasc());
        beneficiario.setCpf(beneficiarioRequestDto.getCpf());
        beneficiario.setEstrangeiro(beneficiarioRequestDto.getEstrangeiro());
        beneficiario.setRaca(RacaEnum.fromString(beneficiarioRequestDto.getRaca()));
        beneficiario.setSexo(SexoEnum.fromString(beneficiarioRequestDto.getSexo()));
        beneficiario.setNomeMae(beneficiarioRequestDto.getNomeMae());
        beneficiario.setEgressoPrisional(beneficiarioRequestDto.getEgressoPrisional());
        beneficiario.setLocalDorme(LocalEnum.fromString(beneficiarioRequestDto.getLocalDorme()));
        beneficiario.setFotoPerfil(beneficiarioRequestDto.getFotoPerfil());
        beneficiario.setSisa(beneficiarioRequestDto.getSisa());
        beneficiario.setObservacao(beneficiarioRequestDto.getObservacao());
        beneficiario.setFuncionario(beneficiarioRequestDto.getFuncionario().getId);
        beneficiario.setEndereco(beneficiarioRequestDto.getEndereco());
        beneficiario.setTipoGenero(beneficiarioRequestDto.getTipoGenero());

        Beneficiario beneficiarioCriado = beneficiarioGateway.salvar(beneficiario);

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
}
