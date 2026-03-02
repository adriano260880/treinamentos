package br.com.aal.kotlinpersistdb.repository

import br.com.aal.kotlinpersistdb.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}