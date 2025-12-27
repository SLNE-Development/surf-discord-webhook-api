package dev.slne.surf.discord.webhook.api.api.ui.builder

import dev.slne.surf.discord.webhook.api.api.ui.*


class MessageDsl {
    private val components = mutableListOf<UiComponent>()

    fun text(markdown: String) {
        components += TextDisplay(content = markdown)
    }

    fun divider() {
        components += Divider()
    }

    fun section(block: SectionBuilder.() -> Unit) {
        components += SectionBuilder().apply(block).build()
    }

    fun media(url: String, alt: String? = null) {
        components += Media(url = url, alt_text = alt)
    }

    fun gallery(vararg urls: String) {
        components += Gallery(
            items = urls.map { Media(url = it) }
        )
    }

    fun callout(
        text: String,
        emoji: String? = null
    ) {
        components += Callout(
            content = text,
            emoji = emoji
        )
    }

    internal fun build(): List<UiComponent> = components
}