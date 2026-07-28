plugins {
    kotlin("jvm") version "2.4.10"
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    api(project(":surf-discord-webhook-api-api"))
    implementation("ch.qos.logback:logback-classic:1.5.38")
}

kotlin {
    jvmToolchain(25)
}
