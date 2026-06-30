package br.com.adriano.orders.service;

import br.com.adriano.kafka.constants.KafkaTopics;
import br.com.adriano.model.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void send(OrderCreatedEvent event) {

        kafkaTemplate.send(
                KafkaTopics.ORDERS,
                event.id().toString(),
                event
        ).whenComplete(this::logResult);
    }

    private void logResult(
            SendResult<String, OrderCreatedEvent> result,
            Throwable ex) {

        if (ex != null) {
            log.error("Erro ao enviar pedido {}", result, ex);
            return;
        }

        RecordMetadata metadata = result.getRecordMetadata();

        log.info("""
                
                                =========================================
                                Pedido enviado com sucesso
                                =========================================
                                Pedido....: {}
                                Topic.....: {}
                                Partition.: {}
                                Offset....: {}
                                Timestamp.: {}
                                =========================================
                """,
                result.getProducerRecord().key(),
                metadata.topic(),
                metadata.partition(),
                metadata.offset(),
                metadata.timestamp());
    }
}
