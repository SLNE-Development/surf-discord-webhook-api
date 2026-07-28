allprojects {
    version = findProperty("version") as String
    group = "dev.slne.surf.discord.webhook.api"
}

plugins {
    id("com.gradleup.shadow") version "9.6.1"
}