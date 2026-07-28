package dev.slne.surf.discord.webhook.api.api.components.builder

import dev.slne.surf.discord.webhook.api.api.components.ComponentType
import dev.slne.surf.discord.webhook.api.api.components.DiscordComponentsPart
import dev.slne.surf.discord.webhook.api.api.components.addition.ButtonStyle
import dev.slne.surf.discord.webhook.api.api.components.addition.Emoji
import dev.slne.surf.discord.webhook.api.api.components.util.buildButton
import dev.slne.surf.discord.webhook.api.api.components.util.buildSelect
import dev.slne.surf.discord.webhook.api.api.components.util.putDefaultValues
import dev.slne.surf.discord.webhook.api.api.components.util.putId
import kotlinx.serialization.json.*

@DiscordComponentsPart
class ActionRowBuilder internal constructor() {
    private val components = mutableListOf<JsonObject>()

    fun button(
        customId: String,
        label: String? = null,
        style: ButtonStyle = ButtonStyle.SECONDARY,
        emoji: Emoji? = null,
        disabled: Boolean = false,
        id: Int? = null
    ) {
        require(style != ButtonStyle.LINK && style != ButtonStyle.PREMIUM) {
            "Use linkButton() or premiumButton() for LINK/PREMIUM types"
        }
        components += buildButton(
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

    fun linkButton(
        url: String,
        label: String? = null,
        emoji: Emoji? = null,
        disabled: Boolean = false,
        id: Int? = null
    ) {
        components += buildButton(
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

    fun premiumButton(skuId: String, disabled: Boolean = false, id: Int? = null) {
        components += buildButton(
            ButtonStyle.PREMIUM,
            null,
            null,
            customId = null,
            url = null,
            skuId = skuId,
            disabled = disabled,
            id = id
        )
    }

    fun stringSelect(
        customId: String,
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        disabled: Boolean = false,
        id: Int? = null,
        block: StringSelectBuilder.() -> Unit
    ) {
        val options = StringSelectBuilder().apply(block).options
        require(options.size in 1..25) { "String select requires 1-25 options" }
        components += buildSelect(
            ComponentType.STRING_SELECT,
            customId,
            placeholder,
            minValues,
            maxValues,
            disabled,
            id
        ) {
            putJsonArray("options") { options.forEach { add(it) } }
        }
    }

    fun userSelect(
        customId: String,
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        disabled: Boolean = false,
        defaultUsers: List<String> = emptyList(),
        id: Int? = null
    ) {
        components += buildSelect(
            ComponentType.USER_SELECT,
            customId,
            placeholder,
            minValues,
            maxValues,
            disabled,
            id
        ) {
            putDefaultValues(users = defaultUsers)
        }
    }

    fun roleSelect(
        customId: String,
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        disabled: Boolean = false,
        defaultRoles: List<String> = emptyList(),
        id: Int? = null
    ) {
        components += buildSelect(
            ComponentType.ROLE_SELECT,
            customId,
            placeholder,
            minValues,
            maxValues,
            disabled,
            id
        ) {
            putDefaultValues(roles = defaultRoles)
        }
    }

    fun mentionableSelect(
        customId: String,
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        disabled: Boolean = false,
        defaultUsers: List<String> = emptyList(),
        defaultRoles: List<String> = emptyList(),
        id: Int? = null
    ) {
        components += buildSelect(
            ComponentType.MENTIONABLE_SELECT,
            customId,
            placeholder,
            minValues,
            maxValues,
            disabled,
            id
        ) {
            putDefaultValues(users = defaultUsers, roles = defaultRoles)
        }
    }

    /** [channelTypes] are Discord-Channel-Type-IDs (0 = Text, 2 = Voice, …). */
    fun channelSelect(
        customId: String,
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        disabled: Boolean = false,
        channelTypes: List<Int> = emptyList(),
        defaultChannels: List<String> = emptyList(),
        id: Int? = null
    ) {
        components += buildSelect(
            ComponentType.CHANNEL_SELECT,
            customId,
            placeholder,
            minValues,
            maxValues,
            disabled,
            id
        ) {
            if (channelTypes.isNotEmpty()) {
                putJsonArray("channel_types") { channelTypes.forEach { add(it) } }
            }
            putDefaultValues(channels = defaultChannels)
        }
    }

    internal fun build(id: Int?): JsonObject {
        require(components.isNotEmpty()) { "Action row requires min. 1 sub-component" }
        return buildJsonObject {
            put("type", ComponentType.ACTION_ROW)
            putId(id)
            putJsonArray("components") { this@ActionRowBuilder.components.forEach { add(it) } }
        }
    }
}