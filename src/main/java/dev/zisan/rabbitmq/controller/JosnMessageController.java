package dev.zisan.rabbitmq.controller;

import dev.zisan.rabbitmq.dto.User;
import dev.zisan.rabbitmq.publisher.RabbitMQJsonProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/json-message")
@RequiredArgsConstructor
public class JosnMessageController {

    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    @PostMapping("/send")
    public User sendJsonMessage(@RequestBody User user) {
        rabbitMQJsonProducer.sendJsonMessage(user);
        return user;
    }
}
