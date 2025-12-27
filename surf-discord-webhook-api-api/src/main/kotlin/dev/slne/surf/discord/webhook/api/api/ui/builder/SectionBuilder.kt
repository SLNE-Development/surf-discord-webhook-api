package dev.slne.surf.discord.webhook.api.api.ui.builder

import dev.slne.surf.discord.webhook.api.api.ui.Media
import dev.slne.surf.discord.webhook.api.api.ui.Section
import dev.slne.surf.discord.webhook.api.api.ui.TextDisplay
import dev.slne.surf.discord.webhook.api.api.ui.Thumbnail
import dev.slne.surf.discord.webhook.api.api.ui.UiComponent

class SectionBuilder {
    private val texts = mutableListOf<TextDisplay>()
    private var accessory: UiComponent? = null

    fun text(markdown: String) {
        texts += TextDisplay(content = markdown)
    }

    fun thumbnail(url: String, alt: String? = null) {
        accessory = Thumbnail(url = url, alt_text = alt)
    }

    fun media(url: String, alt: String? = null) {
        accessory = Media(url = url, alt_text = alt)
    }

    internal fun build(): Section =
        Section(
            components = texts,
            accessory = accessory
        )
}