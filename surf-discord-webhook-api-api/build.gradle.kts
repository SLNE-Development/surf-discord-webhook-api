plugins {
    kotlin("jvm") version "2.4.10"
    kotlin("plugin.serialization") version "1.9.22"
    id("com.gradleup.shadow") version "9.6.1"
    publishing
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-cio:2.3.7")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
}

kotlin {
    jvmToolchain(21)
}

publishing {
    repositories {
        maven("https://reposilite.slne.dev/releases/") {
            name = "slne-repository-releases"

            credentials {
                username = System.getenv("SLNE_RELEASES_REPO_USERNAME")
                password = System.getenv("SLNE_RELEASES_REPO_PASSWORD")
            }
        }
    }
}