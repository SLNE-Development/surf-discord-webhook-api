package dev.slne.surf.discord.webhook.api.api.ui

import kotlinx.serialization.Serializable

@Serializable
data class Divider(
    override val type: Int = 12
) : UiComponent