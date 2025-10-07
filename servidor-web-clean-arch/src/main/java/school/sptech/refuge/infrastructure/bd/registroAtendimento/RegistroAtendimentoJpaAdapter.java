package school.sptech.refuge.infrastructure.bd.registroAtendimento;

import org.springframework.stereotype.Service;
import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.RegistroAtendimentoMapper;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;
import school.sptech.refuge.core.domain.registroAtendimento.RegistroAtendimento;

import java.util.List;
import java.util.Optional;

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
}
