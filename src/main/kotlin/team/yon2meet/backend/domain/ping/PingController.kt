package team.yon2meet.backend.domain.ping

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {
    @GetMapping("/")
    fun ping(): String = "pong"
}
