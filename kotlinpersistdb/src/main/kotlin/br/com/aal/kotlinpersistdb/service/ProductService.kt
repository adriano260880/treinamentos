package br.com.aal.kotlinpersistdb.service

import br.com.aal.kotlinpersistdb.entity.Product
import br.com.aal.kotlinpersistdb.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val repository: ProductRepository
) {
    fun create(product: Product): Product =
        repository.save(product)

    fun findAll(): List<Product> =
        repository.findAll()

    fun findById(id: Long): Product =
        repository.findById(id)
            .orElseThrow { RuntimeException("Product not found!") }
}