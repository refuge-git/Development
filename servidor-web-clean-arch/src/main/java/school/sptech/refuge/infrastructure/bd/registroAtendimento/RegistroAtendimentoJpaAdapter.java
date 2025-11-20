    package school.sptech.refuge.infrastructure.bd.registroAtendimento;

    import org.springframework.stereotype.Service;
    import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
    import school.sptech.refuge.core.application.dto.registroAtendimento.*;
    import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.AtendimentosPorFaixaEtaria;
    import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.AtendimentosPorRacaSexo;
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

        public long getAtendimentosMesAtual() {
            return registroAtendimentoJpaRepository.countAtendimentosMesAtual();
        }

        @Override
        public long getAtendimentosDiaAtual() {
            return registroAtendimentoJpaRepository.countAtendimentosDiaAtual();
        }

        public long getMediaAtendimentoMesAtual() {
            return registroAtendimentoJpaRepository.countMediaAtendimentosMesAtual();
        }

        @Override
        public long getAtendimentosMesAnterior() {
            return registroAtendimentoJpaRepository.countAtendimentosMesAnterior();
        }

        @Override
        public long getNovosCadastrosMes() {
            return registroAtendimentoJpaRepository.countNovosCadastrosMes();
        }

        @Override
        public long getNovosCadastrosMesAnterior() {
            return registroAtendimentoJpaRepository.countNovosCadastrosMesAnterior();
        }

        @Override
        public String getAtividadeMaisRequisitadaMes() {
            List<Object[]> resultados = registroAtendimentoJpaRepository.findAtividadeMaisRequisitadaMes();

            if (resultados != null && !resultados.isEmpty()) {
                Object[] linha = resultados.get(0);
                if (linha != null && linha.length > 0 && linha[0] != null) {
                    return (String) linha[0]; // apenas o nome da atividade
                }
            }

            return "Nenhuma atividade encontrada";
        }

        public String getAtividadeMaisRequisitadaDia() {
            List<Object[]> resultados = registroAtendimentoJpaRepository.findAtividadeMaisRequisitadaDia();

            if (resultados != null && !resultados.isEmpty()) {
                Object[] linha = resultados.get(0);
                if (linha != null && linha.length > 0 && linha[0] != null) {
                    return (String) linha[0]; // apenas o nome da atividade
                }
            }

            return "Nenhuma atividade encontrada";
        }

        @Override
        public String getSegundaAtividadeMaisRequisitadaMes() {
            List<Object[]> resultados = registroAtendimentoJpaRepository.findSegundaAtividadeMaisRequisitadaMes();

            if (resultados != null && !resultados.isEmpty()) {
                Object[] linha = resultados.get(0);
                if (linha != null && linha.length > 0 && linha[0] != null) {
                    return (String) linha[0]; // apenas o nome da atividade
                }
            }

            return "Nenhuma atividade encontrada";
        }

        @Override
        public Double getMediaAtividadeMaisRequisitada() {
            List<Object[]> resultado = registroAtendimentoJpaRepository.findMediaAtividadeMaisRequisitada();
            if (resultado != null && !resultado.isEmpty()) {
                Object[] linha = resultado.get(0);
                return linha[1] != null ? ((Number) linha[1]).doubleValue() : 0.0;
            }
            return 0.0;
        }

        @Override
        public Double getMediaSegundaAtividadeMaisRequisitada() {
            List<Object[]> resultado = registroAtendimentoJpaRepository.findMediaSegundaAtividadeMaisRequisitada();
            if (resultado != null && !resultado.isEmpty()) {
                Object[] linha = resultado.get(0);
                return linha[1] != null ? ((Number) linha[1]).doubleValue() : 0.0;
            }
            return 0.0;
        }


        @Override
        public List<AtendimentosPorFaixaEtaria> contarAtendimentosPorFaixaEtaria() {
            return registroAtendimentoJpaRepository.contarBeneficiariosPorFaixaEtaria();
        }

        @Override
        public List<AtendimentosPorRacaSexo> contarAtendimentosRacaSexoNoMes() {
            return registroAtendimentoJpaRepository.contarBeneficiariosPorRacaSexo();
        }
    }
