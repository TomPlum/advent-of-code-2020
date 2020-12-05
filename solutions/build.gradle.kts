apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")
apply(from = "$rootDir/gradle/logging-dependencies.gradle.kts")

dependencies {
    //Gradle Sub-Project Dependencies
    implementation(project(":implementation"))

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.0")
}