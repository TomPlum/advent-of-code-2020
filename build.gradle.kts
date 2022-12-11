import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.7.21"))
    }
}

plugins {
    idea
    kotlin("jvm") version "1.7.21"
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
        //Standard Libraries
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        //Advent of Code Libraries
        implementation("io.github.tomplum:advent-of-code-libs:2.3.0")
        testImplementation("io.github.tomplum:advent-of-code-test-support:2.3.0")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}