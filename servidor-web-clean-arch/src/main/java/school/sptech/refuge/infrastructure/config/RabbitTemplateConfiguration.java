package school.sptech.refuge.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTemplateConfiguration {

    private final ConnectionFactory connectionFactory;

    public RabbitTemplateConfiguration(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Value("${broker.exchange.name}")
    private String exchangeName;

    @Value("${broker.queue.name}")
    private String queueName;

    @Value("${broker.routing.key.name}")
    private String routingKeyName;

    // 🟢 1. Define o Exchange — DirectExchange usa routingKey
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    // 🟢 2. Cria a fila — durável = true (persistente mesmo após reinício)
    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    // 🟢 3. Faz o binding da fila ao exchange usando a routingKey
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKeyName);
    }

    // 🟢 4. Configura o conversor de mensagem JSON (Jackson)
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 🟢 5. Configura o RabbitTemplate com exchange e conversor
    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(exchangeName);
        rabbitTemplate.setRoutingKey(routingKeyName);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }
}
