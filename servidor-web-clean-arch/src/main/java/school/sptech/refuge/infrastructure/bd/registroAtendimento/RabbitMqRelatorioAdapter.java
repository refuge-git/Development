package school.sptech.refuge.infrastructure.bd.registroAtendimento;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import school.sptech.refuge.core.adapters.PublicarRelatorioGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;

import java.util.List;

@Component
public class RabbitMqRelatorioAdapter implements PublicarRelatorioGateway {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    public RabbitMqRelatorioAdapter(RabbitTemplate rabbitTemplate,
                                    @Value("${broker.queue.name}") String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    @Override
    public void publicarPresencasPorDia(List<PresencaDia> relatorio) {
        rabbitTemplate.convertAndSend(queueName, relatorio);
        System.out.println("✅ Relatório publicado na fila '" + queueName + "' com " + relatorio.size() + " registros.");
    }
}