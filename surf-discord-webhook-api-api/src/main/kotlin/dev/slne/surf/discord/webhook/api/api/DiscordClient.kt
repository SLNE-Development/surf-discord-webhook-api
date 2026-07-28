package dev.slne.surf.discord.webhook.api.api

import dev.slne.surf.discord.webhook.api.api.components.builder.MessageBuilder
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*

class DiscordClient(
    private val webhookUrl: String
) : AutoCloseable {
    private val client = HttpClient(CIO)

    suspend fun sendJson(json: String): Boolean {
        val response = client.post(webhookUrl) {
            url { parameters.append("with_components", "true") }
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            header("User-Agent", "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11")
            setBody(json)
        }

        return response.status.isSuccess()
    }

    suspend fun sendComponents(block: MessageBuilder.() -> Unit) {
        sendJson(MessageBuilder().apply(block).build().toString())
    }

    override fun close() {
        client.close()
    }
}