package school.sptech.refuge.infrastructure.bd.registroAtendimento;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import school.sptech.refuge.core.adapters.PublicarRelatorioGateway;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDia;
import school.sptech.refuge.core.application.dto.registroAtendimento.relatorio.PresencaDiaResponse;

import java.util.List;

@Component
public class RabbitMqRelatorioAdapter implements PublicarRelatorioGateway {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKeyName;

    public RabbitMqRelatorioAdapter(
            RabbitTemplate rabbitTemplate,
            @Value("${broker.exchange.name}") String exchangeName,
            @Value("${broker.routing.key.name}") String routingKeyName
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKeyName = routingKeyName;
    }

    @Override
    public void publicarPresencasPorDia(PresencaDiaResponse relatorio) {
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, relatorio);
        System.out.println("✅ Relatório publicado no exchange '" + exchangeName + "' com routingKey '" + routingKeyName + "' e " + relatorio + " registros.");
    }
}