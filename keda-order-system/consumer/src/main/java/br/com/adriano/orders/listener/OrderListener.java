package br.com.adriano.orders.listener;

import br.com.adriano.kafka.constants.KafkaConsumerGroups;
import br.com.adriano.kafka.constants.KafkaTopics;
import br.com.adriano.model.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    @KafkaListener(topics = KafkaTopics.ORDERS,
                    groupId = KafkaConsumerGroups.ORDER_CONSUMER)
    public void consume(OrderCreatedEvent event) throws InterruptedException {
        log.info("Pedido recebido {}", event.id());

        Thread.sleep(10000);

        log.info("Pedido processado {}", event.id());
    }
}
