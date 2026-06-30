package br.com.adriano.orders.controller;

import br.com.adriano.orders.dto.OrderCreatedEvent;
import br.com.adriano.orders.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderProducer producer;

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody OrderCreatedEvent event) {
        producer.send(event);

        return ResponseEntity.accepted().build();
    }
}
