package dev.slne.surf.discord.webhook.api.api.util

import dev.slne.surf.discord.webhook.api.api.ui.builder.MessageDsl
import dev.slne.surf.discord.webhook.api.api.webhook.DiscordWebhookClient
import dev.slne.surf.discord.webhook.api.api.webhook.WebhookMessage

suspend fun DiscordWebhookClient.sendMessage(
    block: MessageDsl.() -> Unit
) {
    val dsl = MessageDsl().apply(block)
    send(WebhookMessage(dsl.build()))
}