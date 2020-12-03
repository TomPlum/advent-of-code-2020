apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")
apply(from = "$rootDir/gradle/logging-dependencies.gradle.kts")

dependencies {
    //Gradle Sub-Project Dependencies
    implementation(project(":implementation:common"))
    testImplementation(project(":implementation:test-support"))

    //Advent of Code Libs
    implementation("io.github.tomplum:advent-of-code-libs:1.0.2")
}