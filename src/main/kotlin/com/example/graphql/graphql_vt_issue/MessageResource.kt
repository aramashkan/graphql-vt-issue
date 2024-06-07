package com.example.graphql.graphql_vt_issue

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.Instant

@Controller
class MessageResource {

    @SubscriptionMapping(name = "messages")
    fun chatSubscription(): Flux<IncomingMessage> = Flux.create { sink ->
        sink.next(
            IncomingMessage("${Instant.now()}")
        )
    }.sample(
        Duration.ofSeconds(1)
    )

    @QueryMapping
    fun query(): IncomingMessage = IncomingMessage("${Instant.now()}")

}


data class IncomingMessage(
    val text: String
)