package dev.zisan.rabbitmq.consumer;

import dev.zisan.rabbitmq.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQJsonConsumer {
    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void consumeJsonMessage(User user) {
        log.info("JSON User received -> {}", user);
    }
}
