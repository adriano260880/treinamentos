package br.com.aal.kotlinpersistdb.repository

import br.com.aal.kotlinpersistdb.entity.Product
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import kotlin.test.assertEquals

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    lateinit var repository: ProductRepository

    @Test
    fun `should save product`()  {
        val product = Product(name = "Mouse", price = 100.0)
        val saved  = repository.save(product)

        assertEquals("Mouse", saved.name)
        assertEquals(1, repository.count())
    }
}