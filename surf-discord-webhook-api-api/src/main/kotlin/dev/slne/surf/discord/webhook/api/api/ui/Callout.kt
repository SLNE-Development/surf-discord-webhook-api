package dev.slne.surf.discord.webhook.api.api.ui

import kotlinx.serialization.Serializable

@Serializable
data class Callout(
    override val type: Int = 17,
    val content: String,
    val emoji: String? = null
) : UiComponent