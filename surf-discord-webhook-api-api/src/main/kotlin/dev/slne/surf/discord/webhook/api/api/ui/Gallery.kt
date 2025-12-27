package dev.slne.surf.discord.webhook.api.api.ui

import kotlinx.serialization.Serializable

@Serializable
data class Gallery(
    override val type: Int = 15,
    val items: List<Media>
) : UiComponent