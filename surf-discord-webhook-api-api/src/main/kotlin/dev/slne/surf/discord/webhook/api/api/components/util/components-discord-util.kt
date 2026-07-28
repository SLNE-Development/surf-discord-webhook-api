package dev.slne.surf.discord.webhook.api.api.components.util

import dev.slne.surf.discord.webhook.api.api.components.ComponentType
import dev.slne.surf.discord.webhook.api.api.components.addition.ButtonStyle
import dev.slne.surf.discord.webhook.api.api.components.addition.Emoji
import dev.slne.surf.discord.webhook.api.api.components.builder.MessageBuilder
import kotlinx.serialization.json.*

/**
 * Lightweight DSL for Discord Components v2 Webhooks.
 *
 * ```kotlin
 * val json = discordComponentsMessage {
 *     container(accentColor = 0xED4245) {
 *         section {
 *             textDisplay("## Titel")
 *             thumbnail("https://example.com/head.png")
 *         }
 *         separator()
 *         actionRow {
 *             linkButton("https://example.com", label = "Öffnen")
 *         }
 *     }
 * }
 * ```
 * @see MessageBuilder
 */
fun discordComponentsMessage(block: MessageBuilder.() -> Unit): JsonObject =
    MessageBuilder().apply(block).build()

internal fun JsonObjectBuilder.putId(id: Int?) {
    if (id != null) put("id", id)
}

internal fun JsonObjectBuilder.putMedia(key: String, url: String) {
    put(key, buildJsonObject { put("url", url) })
}

internal fun buildTextDisplay(content: String, id: Int?) = buildJsonObject {
    put("type", ComponentType.TEXT_DISPLAY)
    putId(id)
    put("content", content)
}

internal fun buildButton(
    style: ButtonStyle,
    label: String?,
    emoji: Emoji?,
    customId: String?,
    url: String?,
    skuId: String?,
    disabled: Boolean,
    id: Int?
) = buildJsonObject {
    put("type", ComponentType.BUTTON)
    putId(id)
    put("style", style.id)
    label?.let { put("label", it) }
    emoji?.let { put("emoji", it.toJson()) }
    customId?.let { put("custom_id", it) }
    url?.let { put("url", it) }
    skuId?.let { put("sku_id", it) }
    if (disabled) put("disabled", true)
}

internal fun buildSelect(
    type: Int,
    customId: String,
    placeholder: String?,
    minValues: Int,
    maxValues: Int,
    disabled: Boolean,
    id: Int?,
    extra: JsonObjectBuilder.() -> Unit
) = buildJsonObject {
    put("type", type)
    putId(id)
    put("custom_id", customId)
    placeholder?.let { put("placeholder", it) }
    put("min_values", minValues)
    put("max_values", maxValues)
    if (disabled) put("disabled", true)
    extra()
}

internal fun JsonObjectBuilder.putDefaultValues(
    users: List<String> = emptyList(),
    roles: List<String> = emptyList(),
    channels: List<String> = emptyList()
) {
    if (users.isEmpty() && roles.isEmpty() && channels.isEmpty()) return
    putJsonArray("default_values") {
        users.forEach { addJsonObject { put("id", it); put("type", "user") } }
        roles.forEach { addJsonObject { put("id", it); put("type", "role") } }
        channels.forEach { addJsonObject { put("id", it); put("type", "channel") } }
    }
}