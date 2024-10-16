package dev.keeg4n.service;

import dev.keeg4n.model.dto.Customer;
import lombok.extern.slf4j.Slf4j;
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

    public void publishCustomer(Customer customer) {
        CompletableFuture<SendResult<String, Object>> topic = kafkaTemplate.send(
                "customer-events",
                customer
        );

        topic.whenComplete((result, ex) -> {
            if (Objects.isNull(ex)) {
                log.info("Published event {} with offset", customer.toString(), result.getRecordMetadata().offset());
            } else {
                log.error("Failed to publish event {} due to {}", customer.toString(), ex.getMessage());
            }
        });

    }

}
