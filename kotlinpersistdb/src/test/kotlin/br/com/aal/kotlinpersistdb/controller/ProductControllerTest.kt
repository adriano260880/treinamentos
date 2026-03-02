package br.com.aal.kotlinpersistdb.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should create and list products`() {
        val json = """
           {
                "name": "Mouse",
                "price": 19.7
           }
        """

        mockMvc.post("/products") {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isOk() }
        }

        mockMvc.get("/products").andExpect {
            status { isOk() }
        }
    }
}