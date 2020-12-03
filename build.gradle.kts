import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.0"))
    }
}

plugins {
    idea
    maven
    kotlin("jvm") version "1.4.0"
}

allprojects {
    group = "io.github.tomplum"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
        maven("https://maven.pkg.github.com/tomplum/advent-of-code-libs") {
            credentials {
                username = "TomPlum"
                password = System.getenv("GITHUB_PACKAGE_REGISTRY_KEY")
            }
        }
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
        implementation("io.github.tomplum:advent-of-code-libs:1.1.0")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}