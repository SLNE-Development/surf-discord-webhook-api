package dev.slne.surf.discord.webhook.api.api.ui

import kotlinx.serialization.Serializable

@Serializable
data class Section(
    override val type: Int = 11,
    val components: List<TextDisplay>,
    val accessory: UiComponent? = null
) : UiComponent