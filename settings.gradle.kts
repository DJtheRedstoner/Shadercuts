rootProject.name = "Shadercuts"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://repo.essential.gg/repository/maven-public")
        maven("https://maven.architectury.dev")
        maven("https://maven.fabricmc.net")
        maven("https://maven.minecraftforge.net")
    }
    plugins {
        id("gg.essential.loom") version "0.10.0.+"
        id("gg.essential.defaults") version "0.1.18"
        id("com.github.johnrengelman.shadow") version "8.1.0"
    }
}
