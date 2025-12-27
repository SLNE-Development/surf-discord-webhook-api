package dev.slne.surf.discord.webhook.api.api.webhook

import dev.slne.surf.discord.webhook.api.api.ui.UiComponent
import kotlinx.serialization.Serializable

@Serializable
data class WebhookMessage(
    val components: List<UiComponent>
)