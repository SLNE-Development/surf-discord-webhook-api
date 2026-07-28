package dev.slne.surf.discord.webhook.api.test

import dev.slne.surf.discord.webhook.api.api.DiscordClient
import java.net.URI


suspend fun main() {
    val webhook = DiscordClient(
        URI.create("https://discord.com/api/webhooks/WEBHOOK_ID/WEBHOOK_TOKEN").toURL()
    )

    webhook.sendComponents {
        container(accentColor = 0xED4245) {
            section {
                textDisplay("## Titel")
                thumbnail("https://example.com/head.png")
            }
            separator()
            actionRow {
                linkButton("https://example.com", label = "Öffnen")
            }
        }
    }
}