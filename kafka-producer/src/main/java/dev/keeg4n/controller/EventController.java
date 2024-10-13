package dev.keeg4n.controller;

import dev.keeg4n.service.KafkaMessagePublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producer-app")
public class EventController {

    private final KafkaMessagePublisher kafkaMessagePublisher;

    public EventController(KafkaMessagePublisher kafkaMessagePublisher) {
        this.kafkaMessagePublisher = kafkaMessagePublisher;
    }

    @GetMapping("hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("{message}")
    public ResponseEntity<String> addEvent(@PathVariable(name = "message") String message) {
        for(int i = 0; i < 10000; i++) {
            kafkaMessagePublisher.sendMessageToTopic("Message: " + i + " " + message);
        }
        return ResponseEntity.ok("Message published successfully");
    }
}
