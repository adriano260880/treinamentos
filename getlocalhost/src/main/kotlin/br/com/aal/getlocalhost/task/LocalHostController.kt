package br.com.aal.getlocalhost.task

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
class LocalHostController {

    @GetMapping("hostname")
    fun hostname(): String {
        return InetAddress.getLocalHost().hostName
    }
}