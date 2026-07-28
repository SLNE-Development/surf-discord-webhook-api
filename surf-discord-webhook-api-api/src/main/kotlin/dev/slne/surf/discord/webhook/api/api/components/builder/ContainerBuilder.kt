package dev.slne.surf.discord.webhook.api.api.components.builder

import dev.slne.surf.discord.webhook.api.api.components.ComponentType
import dev.slne.surf.discord.webhook.api.api.components.DiscordComponentsPart
import dev.slne.surf.discord.webhook.api.api.components.builder.parent.ComponentParentBuilder
import dev.slne.surf.discord.webhook.api.api.components.util.putId
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

@DiscordComponentsPart
class ContainerBuilder internal constructor() : ComponentParentBuilder() {
    internal fun build(accentColor: Int?, spoiler: Boolean, id: Int?): JsonObject {
        require(components.isNotEmpty()) { "Container requires min. 1 sub component" }
        return buildJsonObject {
            put("type", ComponentType.CONTAINER)
            putId(id)
            accentColor?.let { put("accent_color", it) }
            if (spoiler) put("spoiler", true)
            putJsonArray("components") { this@ContainerBuilder.components.forEach { add(it) } }
        }
    }
}