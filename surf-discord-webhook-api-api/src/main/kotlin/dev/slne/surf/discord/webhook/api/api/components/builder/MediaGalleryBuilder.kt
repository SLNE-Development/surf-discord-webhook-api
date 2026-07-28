package dev.slne.surf.discord.webhook.api.api.components.builder

import dev.slne.surf.discord.webhook.api.api.components.ComponentType
import dev.slne.surf.discord.webhook.api.api.components.DiscordComponentsPart
import dev.slne.surf.discord.webhook.api.api.components.util.putId
import dev.slne.surf.discord.webhook.api.api.components.util.putMedia
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

@DiscordComponentsPart
class MediaGalleryBuilder internal constructor() {
    private val items = mutableListOf<JsonObject>()

    fun item(url: String, description: String? = null, spoiler: Boolean = false) {
        items += buildJsonObject {
            putMedia("media", url)
            description?.let { put("description", it) }
            if (spoiler) put("spoiler", true)
        }
    }

    internal fun build(id: Int?): JsonObject {
        require(items.size in 1..10) { "Media Gallary requires 1-10 items!" }
        return buildJsonObject {
            put("type", ComponentType.MEDIA_GALLERY)
            putId(id)
            putJsonArray("items") { items.forEach { add(it) } }
        }
    }
}