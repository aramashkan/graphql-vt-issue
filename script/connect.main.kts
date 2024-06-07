@file:Repository("https://maven1.citc.ru/repository/public/")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
@file:DependsOn("org.springframework.graphql:spring-graphql:1.3.0")
@file:DependsOn("org.springframework:spring-webflux:6.0.21")
@file:DependsOn("io.projectreactor.netty:reactor-netty-http:1.1.19")
@file:DependsOn("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")
@file:DependsOn("com.fasterxml.jackson.jakarta.rs:jackson-jakarta-rs-json-provider:2.17.1")

import org.springframework.graphql.client.WebSocketGraphQlClient
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


while (true) {
    runBlocking {
        coroutineScope {
            launch { subscribe("without-vt","ws://localhost:8080/api/graphql") }
            launch { subscribe("with-vt","ws://localhost:8081/api/graphql") }
            delay(30000)
        }
    }
}


suspend fun subscribe(name: String, address: String) {
    val client =
        WebSocketGraphQlClient.builder(
            address,
            ReactorNettyWebSocketClient()
        ).build()
    val subscribe = client.document(
        """subscription {
                        messages {
                            text
                        }
                 }""".trimIndent()
    ).execute().map {
        val toEntity = it.field("messages").toEntity(IncomingMessage::class.java)
        println("$name-Received message: $toEntity")
    }.subscribe()

    delay(5000)
    subscribe.dispose()
}


data class IncomingMessage(
    val text: String
)
