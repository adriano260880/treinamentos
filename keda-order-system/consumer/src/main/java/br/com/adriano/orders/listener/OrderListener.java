package br.com.adriano.orders.listener;

import br.com.adriano.kafka.constants.KafkaConsumerGroups;
import br.com.adriano.kafka.constants.KafkaTopics;
import br.com.adriano.model.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class OrderListener {

    @Value("${HOSTNAME:local}")
    private String hostname;

    @Value("${consumer.processing-time:10000}")
    private long processingTime;

    @KafkaListener(topics = KafkaTopics.ORDERS,
                    groupId = KafkaConsumerGroups.ORDER_CONSUMER)
    public void consumeOrder(@Payload OrderCreatedEvent event,
                        @Header(KafkaHeaders.RECEIVED_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                        @Header(KafkaHeaders.OFFSET) long offset,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        logReceived(event, key, partition, offset, topic);

        try {
            Thread.sleep(processingTime);
            logProcessed(event, key, partition, offset, topic);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn(
                    "Consumer {} interrompido durante processamento do pedido {}",
                    hostname,
                    event.id());
        }

    }

    private void logReceived(OrderCreatedEvent event, String key, int partition, long offset, String topic) {
        log.info("""
        
        =========================================
        Pedido recebido
        =========================================
        Consumer...: {}
        DataHora:..: {}
        Id.........: {}
        Cliente....: {}
        Valor......: {}
        Key........: {}
        Topic......: {}
        Partition..: {}
        Offset.....: {}
        =========================================
        """,
                hostname,
                LocalDateTime.now(),
                event.id(),
                event.customer(),
                event.value(),
                key,
                topic,
                partition,
                offset);
    }

    private void logProcessed(OrderCreatedEvent event, String key, int partition, long offset, String topic) {
        log.info("""
        
        =========================================
        Pedido processado
        =========================================
        Consumer...: {}
        DataHora...: {}
        Id.........: {}
        Cliente....: {}
        Valor......: {}
        Key........: {}
        Topic......: {}
        Partition..: {}
        Offset.....: {}
        =========================================
        """,
                hostname,
                LocalDateTime.now(),
                event.id(),
                event.customer(),
                event.value(),
                key,
                topic,
                partition,
                offset);
    }
}
