package school.sptech.refuge.dto.registroAtendimento;

import school.sptech.refuge.dto.tipoAtendimento.TipoAtendimentoResponseDto;
import school.sptech.refuge.entity.Beneficiario;
import school.sptech.refuge.entity.RegistroAtendimento;
import school.sptech.refuge.entity.TipoAtendimento;

import java.util.ArrayList;
import java.util.List;

public class RegistroAtendimentoMapper {

    public RegistroAtendimento toEntity(
            RegistroAtendimentoRequestDto dto,
            TipoAtendimento tipoAtendimento,
            Beneficiario beneficiario
    ) {
        RegistroAtendimento entity = new RegistroAtendimento();
        entity.setData_hora(dto.getData_hora());
        entity.setTipoAtendimento(tipoAtendimento);
        entity.setBeneficiario(beneficiario);
        return entity;
    }

    public RegistroAtendimentoResponseDto toDto(RegistroAtendimento entity) {
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
    }
}
