package br.com.adriano.orders.service;

import br.com.adriano.orders.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void send(OrderCreatedEvent event) {

        kafkaTemplate.send(
                "orders",
                event.id().toString(),
                event
        );
    }
}
