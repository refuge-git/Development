package school.sptech.refuge.service;

import org.springframework.stereotype.Service;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoAtualizarDto;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoListDto;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoMapper;
import school.sptech.refuge.dto.TipoAtendimento.TipoAtendimentoRequestDto;
import school.sptech.refuge.entity.TipoAtendimento;
import school.sptech.refuge.exeption.AtendimentoNaoEncontradoException;
import school.sptech.refuge.repository.TipoAtendimentoRepository;

@Service
public class TipoAtendimentoService {

    private final TipoAtendimentoRepository repository;

    public TipoAtendimentoService(TipoAtendimentoRepository repository) {
        this.repository = repository;
    }


    public TipoAtendimentoListDto cadastrar(TipoAtendimentoRequestDto requestDto) {
        TipoAtendimento tipoAtendimento = TipoAtendimentoMapper.toEntity(requestDto);
        tipoAtendimento = repository.save(tipoAtendimento);
        return TipoAtendimentoMapper.toDTO(tipoAtendimento);
    }


    public TipoAtendimentoListDto atualizar(Integer id, TipoAtendimentoAtualizarDto tipoAtendimentoAtualizarDto) {
        TipoAtendimento tipoAtendimento = repository.findById(id)
                .orElseThrow(() -> new AtendimentoNaoEncontradoException("Tipo de atendimento não encontrado"));

        tipoAtendimento = TipoAtendimentoMapper.toEntityUpdate(tipoAtendimento, tipoAtendimentoAtualizarDto);
        tipoAtendimento = repository.save(tipoAtendimento);
        return TipoAtendimentoMapper.toDTO(tipoAtendimento);
    }


    public TipoAtendimentoListDto getTipoAtendimento(Integer id) {
        TipoAtendimento tipoAtendimento = repository.findById(id)
                .orElseThrow(() -> new AtendimentoNaoEncontradoException("Tipo de atendimento não encontrado"));
        return TipoAtendimentoMapper.toDTO(tipoAtendimento);
    }



    public void deleteTipoAtendimento(Integer id) {
        TipoAtendimento tipoAtendimento = repository.findById(id)
                .orElseThrow(() -> new AtendimentoNaoEncontradoException("Tipo de atendimento não encontrado"));
        repository.delete(tipoAtendimento);
    }
}
