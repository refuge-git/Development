package school.sptech.refuge.infrastructure.bd.registroAtendimento;

import org.springframework.stereotype.Service;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.*;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroAtendimentoJpaAdapter implements RegistroAtendimentoGateway {
    private final RegistroAtendimentoJpaRepository registroAtendimentoJpaRepository;

    public RegistroAtendimentoJpaAdapter(RegistroAtendimentoJpaRepository registroAtendimentoJpaRepository) {
        this.registroAtendimentoJpaRepository = registroAtendimentoJpaRepository;
    }

    @Override
    public RegistroAtendimento salvar(RegistroAtendimento registroAtendimento) {
        RegistroAtendimentoEntity resposta = registroAtendimentoJpaRepository
                .save(RegistroAtendimentoMapper.ofDomain(registroAtendimento));

        return RegistroAtendimentoMapper.ofEntity(resposta);
    }

    @Override
    public List<RegistroAtendimento> listarTodos() {
        return registroAtendimentoJpaRepository.findAll()
                .stream()
                .map(RegistroAtendimentoMapper::ofEntity)
                .toList();
    }

    @Override
    public Optional<RegistroAtendimento> buscarPorId(Integer id) {
        return registroAtendimentoJpaRepository.findById(id)
                .map(RegistroAtendimentoMapper::ofEntity);
    }

    @Override
    public void deletar(Integer id) {
    if (registroAtendimentoJpaRepository.existsById(id)) {
        registroAtendimentoJpaRepository.deleteById(id);
    }
    }

    @Override
    public boolean existePorId(Integer id) {
        return false;
    }

    @Override
    public Long contarBeneficiariosAtendidosNoMes() {
        return registroAtendimentoJpaRepository.countBeneficiariosAtendidosNoMes();
    }

    @Override
    public List<PresencaDia> contarPresencasPorDiaNoMes() {
        return registroAtendimentoJpaRepository.countPresencasPorDiaNoMes();
    }

    @Override
    public List<AtendimentosMesDto> buscarAtendimentosMes() {
        return registroAtendimentoJpaRepository.buscarAtendimentosMes().stream()
                .map(r -> new AtendimentosMesDto((String) r[0], ((Number) r[1]).longValue()))
                .collect(Collectors.toList());
    }



    @Override
    public List<ServicosPorSemanaDto> buscarServicosPorSemana() {
        List<Object[]> resultados = registroAtendimentoJpaRepository.buscarServicosPorSemana();

        return resultados.stream()
                .map(obj -> new ServicosPorSemanaDto(
                        (String) obj[0],
                        ((Number) obj[2]).intValue(),
                        ((Number) obj[3]).intValue(),
                        ((Number) obj[4]).intValue()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<AtendimentosDiaDto> buscarAtendimentosDia() {
        return registroAtendimentoJpaRepository.buscarAtendimentosDia().stream()
                .map(r -> {
                    Long qtd = null;
                    if (r[1] instanceof Number) {
                        qtd = ((Number) r[1]).longValue();
                    } else if (r[1] != null) {
                        qtd = Long.parseLong(r[1].toString());
                    }
                    return new AtendimentosDiaDto((String) r[0], qtd);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AtendimentosSemanaDto> buscarAtendimentosSemana() {
        return registroAtendimentoJpaRepository.buscarAtendimentosSemana().stream()
                .map(r -> new AtendimentosSemanaDto((String) r[0], ((Number) r[1]).longValue()))
                .collect(Collectors.toList());
    }

}
