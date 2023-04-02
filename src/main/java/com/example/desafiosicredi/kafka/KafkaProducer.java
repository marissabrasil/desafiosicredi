package com.example.desafiosicredi.kafka;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KafkaProducer {
    public KafkaTemplate<String, String> kafkaTemplate;
    private final String topic = "resultados";

    public void send(String message){
        kafkaTemplate.send(topic, message);
    }
}
