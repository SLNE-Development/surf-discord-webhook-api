package dev.slne.surf.discord.webhook.api.api.components.builder.parent

import dev.slne.surf.discord.webhook.api.api.components.ComponentType
import dev.slne.surf.discord.webhook.api.api.components.DiscordComponentsPart
import dev.slne.surf.discord.webhook.api.api.components.addition.SeparatorSpacing
import dev.slne.surf.discord.webhook.api.api.components.builder.ActionRowBuilder
import dev.slne.surf.discord.webhook.api.api.components.builder.MediaGalleryBuilder
import dev.slne.surf.discord.webhook.api.api.components.builder.SectionBuilder
import dev.slne.surf.discord.webhook.api.api.components.util.buildTextDisplay
import dev.slne.surf.discord.webhook.api.api.components.util.putId
import dev.slne.surf.discord.webhook.api.api.components.util.putMedia
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@DiscordComponentsPart
abstract class ComponentParentBuilder internal constructor() {
    protected val components = mutableListOf<JsonObject>()

    fun textDisplay(content: String, id: Int? = null) {
        components += buildTextDisplay(content, id)
    }

    fun section(id: Int? = null, block: SectionBuilder.() -> Unit) {
        components += SectionBuilder().apply(block).build(id)
    }

    fun mediaGallery(id: Int? = null, block: MediaGalleryBuilder.() -> Unit) {
        components += MediaGalleryBuilder().apply(block).build(id)
    }

    /** [attachmentUrl] has to link to an file, e.g. `attachment://file.txt`. */
    fun file(attachmentUrl: String, spoiler: Boolean = false, id: Int? = null) {
        components += buildJsonObject {
            put("type", ComponentType.FILE)
            putId(id)
            putMedia("file", attachmentUrl)
            if (spoiler) put("spoiler", true)
        }
    }

    fun separator(
        divider: Boolean = true,
        spacing: SeparatorSpacing = SeparatorSpacing.SMALL,
        id: Int? = null
    ) {
        components += buildJsonObject {
            put("type", ComponentType.SEPARATOR)
            putId(id)
            put("divider", divider)
            put("spacing", spacing.id)
        }
    }

    fun actionRow(id: Int? = null, block: ActionRowBuilder.() -> Unit) {
        components += ActionRowBuilder().apply(block).build(id)
    }
}