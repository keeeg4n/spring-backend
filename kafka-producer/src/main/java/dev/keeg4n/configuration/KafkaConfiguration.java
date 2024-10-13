package dev.keeg4n.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic createTopic() {
        return new NewTopic("topic-batman", 3, (short) 1);
    }

}
