package dev.slne.surf.discord.webhook.api.api.components.builder

import dev.slne.surf.discord.webhook.api.api.components.DiscordComponentsPart
import dev.slne.surf.discord.webhook.api.api.components.builder.parent.ComponentParentBuilder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

private const val FLAG_SUPPRESS_NOTIFICATIONS = 1 shl 12
private const val FLAG_IS_COMPONENTS_V2 = 1 shl 15

@DiscordComponentsPart
class MessageBuilder internal constructor() : ComponentParentBuilder() {
    var username: String? = null
    var avatarUrl: String? = null
    var threadName: String? = null

    private var flags = FLAG_IS_COMPONENTS_V2

    fun suppressNotifications() {
        flags = flags or FLAG_SUPPRESS_NOTIFICATIONS
    }

    fun container(
        accentColor: Int? = null,
        spoiler: Boolean = false,
        id: Int? = null,
        block: ContainerBuilder.() -> Unit
    ) {
        components += ContainerBuilder().apply(block).build(accentColor, spoiler, id)
    }

    internal fun build(): JsonObject {
        require(components.isNotEmpty()) { "Message requires min. 1 sub-component" }
        return buildJsonObject {
            put("flags", flags)
            username?.let { put("username", it) }
            avatarUrl?.let { put("avatar_url", it) }
            threadName?.let { put("thread_name", it) }
            putJsonArray("components") { this@MessageBuilder.components.forEach { add(it) } }
        }
    }
}