package dev.slne.surf.discord.webhook.api.api.ui
import kotlinx.serialization.Serializable

@Serializable
data class TextDisplay(
    override val type: Int = 10,
    val content: String
) : UiComponent