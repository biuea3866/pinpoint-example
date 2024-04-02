package pinpoint.example.paymentserver

import org.springframework.web.bind.annotation.*

@RestController
class PaymentController {
    @GetMapping(
        value = ["/payment"],
        produces = ["application/json; charset=utf-8"]
    )
    fun payment(@RequestHeader("X-USER-ID") userId: Long): String {
        if (userId != 1L) throw RuntimeException("Invalid user id")

        return "payment"
    }
}
