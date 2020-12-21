import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.21"))
    }
}

plugins {
    idea
    kotlin("jvm") version "1.4.21"
}

allprojects {
    group = "io.github.tomplum"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
        jcenter()
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
        implementation("io.github.tomplum:advent-of-code-libs:1.5.1")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}