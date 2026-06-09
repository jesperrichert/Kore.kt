plugins {
    id("java-library")
    kotlin("jvm") version "2.4.0"
    kotlin("plugin.serialization") version "2.4.0"
    id("maven-publish")
    id("org.jetbrains.dokka") version "2.+"
}

version = "0.1.0"
group = "zip.jespersen"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    // Database
    api("org.ktorm:ktorm-core:4.+")
    api("org.ktorm:ktorm-support-postgresql:4.+")
    api("org.ktorm:ktorm-support-mysql:4.+")
    api("org.postgresql:postgresql:42.+")
    api("eu.vendeli:rethis:0.4.3")

    // Kotlin
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.+")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // ENV
    api("io.github.cdimascio:dotenv-kotlin:6.+")

    // yamlkt
    api("net.mamoe.yamlkt:yamlkt:0.+")
}

kotlin {
    jvmToolchain(25)
}

tasks {
    java {
        withSourcesJar()
        withJavadocJar()
    }
    publishing {
        repositories {
            maven {
                name = "Reposilite"
                url = uri("https://repo.jespersen.zip/releases")
                credentials {
                    username = System.getenv("REPOSILITE_USER") ?: System.getProperty("REPOSILITE_USER") ?: "USERNAME"
                    password = System.getenv("REPOSILITE_TOKEN") ?: System.getProperty("REPOSILITE_TOKEN") ?: "TOKEN"
                }
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
        publications {
            create<MavenPublication>("reposilite") {
                from(components["java"])
                artifactId = "Kore.kt"
                groupId = group as String
                version = version
            }
        }
    }
}

