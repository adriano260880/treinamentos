package br.com.aal.kotlinpersistdb.controller

import br.com.aal.kotlinpersistdb.entity.Product
import br.com.aal.kotlinpersistdb.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(
    private val service: ProductService
) {
    @PostMapping
    fun create(@RequestBody product: Product): Product =
        service.create(product)

    @GetMapping
    fun findAll(): List<Product> =
        service.findAll()

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): Product =
        service.findById(id)
}