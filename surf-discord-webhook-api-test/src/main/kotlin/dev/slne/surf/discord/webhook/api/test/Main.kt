package dev.slne.surf.discord.webhook.api.test

import dev.slne.surf.discord.webhook.api.api.DiscordClient


suspend fun main() {
    val webhook = DiscordClient(
        "https://discord.com/api/webhooks/WEBHOOK_ID/WEBHOOK_TOKEN"
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