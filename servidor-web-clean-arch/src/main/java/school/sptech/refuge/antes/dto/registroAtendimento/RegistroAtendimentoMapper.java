package school.sptech.refuge.antes.dto.registroAtendimento;

import school.sptech.refuge.antes.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.antes.dto.endereco.EnderecoListDto;
import school.sptech.refuge.antes.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.antes.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.antes.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.antes.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.antes.entity.*;
import school.sptech.refuge.entity.*;

import java.util.List;

public class RegistroAtendimentoMapper {

    public static RegistroAtendimentoResponseDto toListagemDto(RegistroAtendimento entity) {
        if (entity == null) return null;

        FuncionarioListDto funcionarioDto = toFuncionarioDto(entity.getBeneficiario().getFuncionario());

        EnderecoListDto enderecoDto = toEnderecoDto(entity.getBeneficiario().getEndereco());

        TipoGeneroListDto generoDto = new TipoGeneroListDto(
                entity.getBeneficiario().getTipoGenero().getId(),
                entity.getBeneficiario().getTipoGenero().getNome(),
                entity.getBeneficiario().getTipoGenero().getDescricao()
        );

        TipoSexualidadeListDto sexualidadeDto = new TipoSexualidadeListDto(
                entity.getBeneficiario().getTipoSexualidade().getId(),
                entity.getBeneficiario().getTipoSexualidade().getNome(),
                entity.getBeneficiario().getTipoSexualidade().getDescricao()
        );

        String descricaoStatus = entity.getBeneficiario().getStatus() != null
                ? entity.getBeneficiario().getStatus().getDescricaoStatus()
                : "Status não definido";

        BeneficarioListDto beneficiarioDto = new BeneficarioListDto(
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
                enderecoDto,
                generoDto,
                sexualidadeDto
        );

        TipoAtendimentoResponseDto tipoAtendimentoDto = new TipoAtendimentoResponseDto(
                entity.getTipoAtendimento().getId(),
                entity.getTipoAtendimento().getNome(),
                entity.getTipoAtendimento().getDescricao(),
                entity.getTipoAtendimento().getDataCriacao(),
                funcionarioDto
        );

        return new RegistroAtendimentoResponseDto(
                entity.getId(),
                entity.getDataHora(),
                tipoAtendimentoDto,
                beneficiarioDto
        );
    }

    public static List<RegistroAtendimentoResponseDto> toListagemDtos(List<RegistroAtendimento> registroAtendimentos){
        if (registroAtendimentos == null) {
            return null;
        }

        return registroAtendimentos.stream()
                .map(RegistroAtendimentoMapper::toListagemDto)
                .toList();
    }

    /*public RegistroAtendimentoResponseDto toDto(RegistroAtendimento entity) {
        RegistroAtendimentoResponseDto dto = new RegistroAtendimentoResponseDto();

        dto.setId(entity.getId());
        dto.setData_hora(entity.getData_hora());

        TipoAtendimentoResponseDto tipoDto = new TipoAtendimentoResponseDto();
        tipoDto.setId(entity.getTipoAtendimento().getId_TipoAtendimento());
        tipoDto.setNome(entity.getTipoAtendimento().getNome());
        dto.setTipoAtendimento(tipoDto);

        Beneficiario beneficiarioDto = new Beneficiario();
        beneficiarioDto.setId(entity.getBeneficiario().getId());
        beneficiarioDto.setNomeRegistro(entity.getBeneficiario().getNomeRegistro());
        dto.setBeneficiario(beneficiarioDto);

        return dto;
    }

    public List<RegistroAtendimentoResponseDto> toListDto(List<RegistroAtendimento> registros) {
        List<RegistroAtendimentoResponseDto> dtos = new ArrayList<>();
        for (RegistroAtendimento registro : registros) {
            dtos.add(toDto(registro));
        }
        return dtos;
    }*/

    public static RegistroAtendimento toEntity(RegistroAtendimentoRequestDto dto){
        if (dto == null) {
            return null;
        }

        TipoAtendimento tipoAtendimento = new TipoAtendimento();
        tipoAtendimento.setId(dto.getIdTipoAtendimento());

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(dto.getIdBeneficiario());

        RegistroAtendimento entity = new RegistroAtendimento(
                null,
                dto.getDataHora(),
                tipoAtendimento,
                beneficiario
        );

        return entity;
    }

    public static RegistroAtendimento toEntity(RegistroAtendimentoRequestDto dto, Integer id){
        if (dto == null) {
            return null;
        }

        TipoAtendimento tipoAtendimento = new TipoAtendimento();
        tipoAtendimento.setId(dto.getIdTipoAtendimento());

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(dto.getIdBeneficiario());

        RegistroAtendimento entity = new RegistroAtendimento(
                id,
                dto.getDataHora(),
                tipoAtendimento,
                beneficiario
        );

        return entity;
    }

    private static FuncionarioListDto toFuncionarioDto(Funcionario funcionario) {
        if (funcionario == null) return null;
        return new FuncionarioListDto(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getTelefone()
        );
    }

    private static EnderecoListDto toEnderecoDto(Endereco endereco) {
        if (endereco == null) return null;
        return new EnderecoListDto(
                endereco.getId(),
                endereco.getTipoLogradouro(),
                endereco.getNomeLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getNomeLocalidade(),
                endereco.getSiglaCidade()
        );
    }
}
