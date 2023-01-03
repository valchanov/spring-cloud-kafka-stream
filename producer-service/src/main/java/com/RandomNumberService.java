package com;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class RandomNumberService {
    private static final String KAFKA_TOPIC = "numbers";
    private final KafkaTemplate<String, Integer> kafkaTemplate;

    @Scheduled(fixedRate = 1000, initialDelay = 3L)
    public void publish() {
        int randomNumber = new Random().nextInt(1000);
        kafkaTemplate.send(KAFKA_TOPIC, randomNumber);

        log.info("Producer service sends message with number: {}", randomNumber);
    }
}
