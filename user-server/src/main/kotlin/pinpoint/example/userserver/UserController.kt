package pinpoint.example.userserver

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping(
        value = ["/user"],
        produces = ["application/json; charset=utf-8"]
    )
    fun getUser(
        @RequestHeader("X-USER-ID") userId: Long
    ): String {
        if (userId != 1L) throw RuntimeException("Invalid user id")

        return "user"
    }
}