package dev.keeg4n.service;

import dev.keeg4n.model.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerKafkaListener {

    @KafkaListener(topics = "customer-events", groupId = "customer-group")
    public void customerConsumer(Customer customer) {
        log.info("Customer details: {}", customer);
    }

}
