package dev.keeg4n.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    private final String topicName = "topic-batman";

    // Consumers are there as a demo!! Not a good way to create consumers like these
//    @KafkaListener(topics = topicName, groupId = "jt-group-1")
//    public void consumer1(String message) {
//        log.info("Consumer 1 message: {}", message);
//    }
//
//    @KafkaListener(topics = topicName, groupId = "jt-group-1")
//    public void consumer2(String message) {
//        log.info("Consumer 2 message: {}", message);
//    }
//
//    @KafkaListener(topics = topicName, groupId = "jt-group-1")
//    public void consumer3(String message) {
//        log.info("Consumer 3 message: {}", message);
//    }

}
