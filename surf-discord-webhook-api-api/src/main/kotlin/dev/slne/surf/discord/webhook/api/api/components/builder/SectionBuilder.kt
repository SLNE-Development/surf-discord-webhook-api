package dev.slne.surf.discord.webhook.api.api.components.builder

import dev.slne.surf.discord.webhook.api.api.components.ComponentType
import dev.slne.surf.discord.webhook.api.api.components.DiscordComponentsPart
import dev.slne.surf.discord.webhook.api.api.components.addition.ButtonStyle
import dev.slne.surf.discord.webhook.api.api.components.addition.Emoji
import dev.slne.surf.discord.webhook.api.api.components.util.buildButton
import dev.slne.surf.discord.webhook.api.api.components.util.buildTextDisplay
import dev.slne.surf.discord.webhook.api.api.components.util.putId
import dev.slne.surf.discord.webhook.api.api.components.util.putMedia
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

@DiscordComponentsPart
class SectionBuilder internal constructor() {
    private val texts = mutableListOf<JsonObject>()
    private var accessory: JsonObject? = null

    fun textDisplay(content: String, id: Int? = null) {
        texts += buildTextDisplay(content, id)
    }

    fun thumbnail(
        url: String,
        description: String? = null,
        spoiler: Boolean = false,
        id: Int? = null
    ) {
        accessory = buildJsonObject {
            put("type", ComponentType.THUMBNAIL)
            putId(id)
            putMedia("media", url)
            description?.let { put("description", it) }
            if (spoiler) put("spoiler", true)
        }
    }

    fun accessoryButton(
        customId: String,
        label: String? = null,
        style: ButtonStyle = ButtonStyle.SECONDARY,
        emoji: Emoji? = null,
        disabled: Boolean = false,
        id: Int? = null
    ) {
        accessory = buildButton(
            style,
            label,
            emoji,
            customId = customId,
            url = null,
            skuId = null,
            disabled = disabled,
            id = id
        )
    }

    fun accessoryLinkButton(
        url: String,
        label: String? = null,
        emoji: Emoji? = null,
        disabled: Boolean = false,
        id: Int? = null
    ) {
        accessory = buildButton(
            ButtonStyle.LINK,
            label,
            emoji,
            customId = null,
            url = url,
            skuId = null,
            disabled = disabled,
            id = id
        )
    }

    internal fun build(id: Int?): JsonObject {
        require(texts.size in 1..3) { "Section requires 1-3 text displays." }
        val accessory =
            requireNotNull(accessory) { "Section requires an accessory (thumbnail/accessoryButton)" }
        return buildJsonObject {
            put("type", ComponentType.SECTION)
            putId(id)
            putJsonArray("components") { texts.forEach { add(it) } }
            put("accessory", accessory)
        }
    }
}