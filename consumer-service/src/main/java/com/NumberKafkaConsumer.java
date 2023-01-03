package com;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class NumberKafkaConsumer {

    @Bean
    public Consumer<KStream<String, String>> numberService() {
        return kstream -> kstream.filter((key, value) -> value.contains("Even"))
                                 .foreach((key, value) -> log.info("Consumed number even number is: {}", value));
    }
}
