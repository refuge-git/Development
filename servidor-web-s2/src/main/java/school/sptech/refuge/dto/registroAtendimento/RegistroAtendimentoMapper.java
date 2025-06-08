package school.sptech.refuge.dto.registroAtendimento;

import school.sptech.refuge.dto.beneficiario.BeneficarioListDto;
import school.sptech.refuge.dto.categoria.CategoriaListDto;
import school.sptech.refuge.dto.condicaosaude.CondicaoSaudeListDto;
import school.sptech.refuge.dto.endereco.EnderecoListDto;
import school.sptech.refuge.dto.funcionario.FuncionarioListDto;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoMapper;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.dto.tipogenero.TipoGeneroListDto;
import school.sptech.refuge.dto.tiposexualidade.TipoSexualidadeListDto;
import school.sptech.refuge.entity.*;

import java.util.ArrayList;
import java.util.List;

public class RegistroAtendimentoMapper {

    public static RegistroAtendimentoResponseDto toListagemDto(RegistroAtendimento entity) {
        if (entity == null) return null;

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

        TipoAtendimentoResponseDto tipoAtendimento = new TipoAtendimentoResponseDto(
                entity.getTipoAtendimento().getId(),
                entity.getTipoAtendimento().getNome(),
                entity.getTipoAtendimento().getDescricao(),
                entity.getTipoAtendimento().getDataCriacao(),
                funcionarioDto
        );

        return new RegistroAtendimentoResponseDto(
                entity.getId(),
                entity.getDataHora(),
                tipoAtendimento,
                beneficarioListDto
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
}
