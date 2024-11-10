package team.yon2meet.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Yon2meetBackendApplication

fun main(args: Array<String>) {
    runApplication<Yon2meetBackendApplication>(*args)
}

const val BASE_PACKAGE = "team.yon2meet.backend"
