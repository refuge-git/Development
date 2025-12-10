    package school.sptech.refuge.core.application.usecase.registroAtendimento;

    import school.sptech.refuge.core.adapters.RegistroAtendimentoGateway;
    import school.sptech.refuge.core.application.dto.registroAtendimento.AtendimentosDiaIntervaloDto;

    import java.time.LocalDate;
    import java.util.List;

    public class BuscarAtendimentosPorDiaIntervaloUseCase {

        private final RegistroAtendimentoGateway gateway;

        public BuscarAtendimentosPorDiaIntervaloUseCase(RegistroAtendimentoGateway gateway) {
            this.gateway = gateway;
        }

        public List<AtendimentosDiaIntervaloDto> execute(LocalDate inicio, LocalDate fim) {
            return gateway.buscarAtendimentosAgrupadoPorDia(inicio, fim);
        }
    }
