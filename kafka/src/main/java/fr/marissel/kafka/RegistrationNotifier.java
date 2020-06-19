package fr.marissel.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistrationNotifier {

    @KafkaListener(topics = "registration", groupId = "group.id")
    public void consume(String message) {
        log.info(String.format("$$ -> Consumed Message -> %s", message));
    }
}
