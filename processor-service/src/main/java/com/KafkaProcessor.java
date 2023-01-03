package com;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class KafkaProcessor {

    @Bean
    public Function<KStream<String, Integer>, KStream<String, String>> numberProcessor() {
        return kstream ->
            kstream.map((key, value) -> {
                String oddOrEvenNumber = value % 2 == 0 ? "Even" : "Odd";
                log.info("Published number: {} is {}.", value, oddOrEvenNumber);

                return new KeyValue<>(key, "Number: " + value + " is " + oddOrEvenNumber);
            });
    }
}
