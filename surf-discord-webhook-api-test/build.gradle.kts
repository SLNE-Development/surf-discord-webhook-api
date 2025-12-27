plugins {
    kotlin("jvm") version "2.2.21"
}


repositories {
    mavenCentral()
}

dependencies {
    api(project(":surf-discord-webhook-api-api"))
}

kotlin {
    jvmToolchain(21)
}
