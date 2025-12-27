package dev.slne.surf.discord.webhook.api.test

import dev.slne.surf.discord.webhook.api.api.util.sendMessage
import dev.slne.surf.discord.webhook.api.api.webhook.DiscordWebhookClient


suspend fun main() {
    val webhook = DiscordWebhookClient(
        "https://discord.com/api/webhooks/WEBHOOK_ID/WEBHOOK_TOKEN"
    )

    webhook.sendMessage {
        text("# 🚀 Deployment abgeschlossen")

        callout(
            text = "Dieses Deployment lief ohne Fehler.",
            emoji = "✅"
        )

        divider()

        section {
            text(
                """
                **Projekt:** Backend-Service  
                **Version:** v1.8.3  
                **Umgebung:** Production
                """
            )

            thumbnail(
                url = "https://example.com/logo.png",
                alt = "Projekt Logo"
            )
        }

        media(
            url = "https://example.com/screenshot.png",
            alt = "Deployment Screenshot"
        )
        gallery(
            "https://example.com/chart1.png",
            "https://example.com/chart2.png"
        )
        section {
            text(
                "🕒 **Zeit:** 14:52\n" +
                        "👤 **Ausgelöst von:** GitHub Actions"
            )
        }
    }
}