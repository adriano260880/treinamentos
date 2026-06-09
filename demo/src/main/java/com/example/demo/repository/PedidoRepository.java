package com.example.demo.repository;

import com.example.demo.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {


    @Query("""
            
            SELECT p
            FROM Pedido p
            JOIN FETCH p.itens
            WHERE p.id = :id
            
            """)
    Optional<Pedido> buscarCompleto(Long id);
}
