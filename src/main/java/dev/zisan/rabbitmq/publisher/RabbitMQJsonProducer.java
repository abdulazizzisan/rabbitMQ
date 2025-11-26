package dev.zisan.rabbitmq.publisher;

import dev.zisan.rabbitmq.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RabbitMQJsonProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.key.routing.json}")
    private String routingKey;


    public void sendJsonMessage(User user) {
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
        log.info("JSON User sent to RabbitMQ successfully. User: {}", user);
    }

}
