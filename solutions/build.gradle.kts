apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

dependencies {
    //Gradle Sub-Project Dependencies
    implementation(project(":implementation"))
    implementation(project(":implementation:common"))
}