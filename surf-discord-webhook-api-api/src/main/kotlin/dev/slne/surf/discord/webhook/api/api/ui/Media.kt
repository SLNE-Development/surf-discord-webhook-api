package dev.slne.surf.discord.webhook.api.api.ui

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    override val type: Int = 14,
    val url: String,
    val alt_text: String? = null
) : UiComponent