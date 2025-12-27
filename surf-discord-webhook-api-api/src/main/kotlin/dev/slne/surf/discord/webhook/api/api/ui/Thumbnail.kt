package dev.slne.surf.discord.webhook.api.api.ui

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    override val type: Int = 13,
    val url: String,
    val alt_text: String? = null
) : UiComponent