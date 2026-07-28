package dev.slne.surf.discord.webhook.api.api.components.builder

import dev.slne.surf.discord.webhook.api.api.components.DiscordComponentsPart
import dev.slne.surf.discord.webhook.api.api.components.addition.Emoji
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@DiscordComponentsPart
class StringSelectBuilder internal constructor() {
    internal val options = mutableListOf<JsonObject>()

    fun option(
        label: String,
        value: String,
        description: String? = null,
        emoji: Emoji? = null,
        default: Boolean = false
    ) {
        options += buildJsonObject {
            put("label", label)
            put("value", value)
            description?.let { put("description", it) }
            emoji?.let { put("emoji", it.toJson()) }
            if (default) put("default", true)
        }
    }
}