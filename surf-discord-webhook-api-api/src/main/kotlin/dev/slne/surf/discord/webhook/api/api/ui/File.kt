package dev.slne.surf.discord.webhook.api.api.ui

import kotlinx.serialization.Serializable

@Serializable
data class File(
    override val type: Int = 16,
    val filename: String,
    val size: Int
) : UiComponent