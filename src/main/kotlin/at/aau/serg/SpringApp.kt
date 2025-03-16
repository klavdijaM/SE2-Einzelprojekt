package at.aau.serg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication // marks it as springBoot application
class SpringApp

fun main(args: Array<String>) {
    runApplication<SpringApp>(*args) // starts the springBoot framework
}
