plugins {
    id("java")
    id("gg.essential.loom")
    id("gg.essential.defaults")
    id("com.github.johnrengelman.shadow")
}

version = "1.0.0"

java.toolchain {
    languageVersion.set(JavaLanguageVersion.of(8))
}

sourceSets {
    val dummy by creating {
        compileClasspath += main.get().compileClasspath
    }
    main.get().compileClasspath += dummy.output
}

tasks.processResources {
    inputs.property("version", version)

    filesMatching("mcmod.info") {
        expand(mapOf("version" to version))
    }
}

tasks.processResources {
    from("LICENSE.txt") {
        rename { "LICENSE_${project.name}" }
    }
}

tasks.shadowJar {
    configurations = listOf()
    relocate("ofconfig.", "")
}

tasks.remapJar {
    input.set(tasks.shadowJar.get().archiveFile)
}
