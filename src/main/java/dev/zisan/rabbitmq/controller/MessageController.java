package dev.zisan.rabbitmq.controller;

import dev.zisan.rabbitmq.publisher.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {

    private final RabbitMQProducer producer;

    @GetMapping("/publish")
    public ResponseEntity<Map<String, String >> publishMessage(@RequestParam String message){
        producer.sendMessage(message);
        return ResponseEntity.ok(Map.of("message", "Message sent to RabbitMQ Successfully"));
    }
}
