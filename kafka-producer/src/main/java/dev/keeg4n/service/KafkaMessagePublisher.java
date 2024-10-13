package dev.keeg4n.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> topic = kafkaTemplate.send(
                "topic-batman",
                message
        );

        topic.whenComplete((result, ex) -> {
            if (Objects.isNull(ex)) {
                log.info("Sent message = [{}] with offset = [{}]", message, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message = [{}] due to : {}", message, ex.getMessage());
            }
        });
    }

}
