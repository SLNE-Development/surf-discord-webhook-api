package dev.slne.surf.discord.webhook.api.api.components.addition

import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class Emoji private constructor(
    private val name: String,
    private val id: String?,
    private val animated: Boolean
) {
    internal fun toJson() = buildJsonObject {
        put("name", name)
        if (id != null) {
            put("id", id)
            if (animated) put("animated", true)
        }
    }

    companion object {
        fun unicode(emoji: String) = Emoji(emoji, null, false)
        fun custom(name: String, id: String, animated: Boolean = false) = Emoji(name, id, animated)
    }
}