package com.example.demo.entity;

import com.example.demo.util.StatusPedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private List<ItemPedido> itens = new ArrayList<>();

    public void adicionarItem(ItemPedido item) {

        itens.add(item);
        item.setPedido(this);
    }

    public void removerItem(ItemPedido item) {

        itens.remove(item);
        item.setPedido(null);
    }

    public BigDecimal calcularTotal() {

        return itens.stream()
                .map(ItemPedido::calcularSubtotal)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}
