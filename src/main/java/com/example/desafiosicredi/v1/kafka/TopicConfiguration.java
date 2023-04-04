package com.example.desafiosicredi.v1.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {
    private String topicName = "resultados";

    public NewTopic resultadosTopic(){
        return TopicBuilder
                .name(topicName)
                .build();
    }
}
