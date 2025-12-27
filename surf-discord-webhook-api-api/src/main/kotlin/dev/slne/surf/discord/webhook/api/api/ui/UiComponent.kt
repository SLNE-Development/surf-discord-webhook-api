package dev.slne.surf.discord.webhook.api.api.ui

import kotlinx.serialization.Serializable

@Serializable
sealed interface UiComponent {
    val type: Int
}