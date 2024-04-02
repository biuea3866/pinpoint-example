package pinpoint.example.orderserver

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@RestController
class OrderController {
    private val objectMapper = ObjectMapper()
    private val restClient = RestClient.create()

    @PostMapping(
        value = ["/orders"],
        produces = ["application/json; charset=utf-8"],
    )
    fun createOrder(
        @RequestHeader("X-USER-ID") userId: Long,
        @RequestBody body: CreateOrder
    ): String {
        val callUser = restClient.get()
            .uri("http://user-server:8084/user")
            .header("Content-Type", "application/json")
            .header("X-USER-ID", userId.toString())
            .retrieve()
            .toBodilessEntity()
        val callPayment = restClient.get()
            .uri("http://payment-server:8083/payment")
            .header("Content-Type", "application/json")
            .header("X-USER-ID", userId.toString())
            .retrieve()
            .toBodilessEntity()

        return "ok"
    }

    @GetMapping(
        value = ["/orders"],
        produces = ["application/json; charset=utf-8"],
    )
    fun getOrders(
        @RequestHeader("X-USER-ID") userId: Long
    ): String {
        return "orders"
    }
}

data class CreateOrder(
    val productId: Long,
    val quantity: Int,
    val price: Int
)