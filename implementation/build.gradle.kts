apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")
apply(from = "$rootDir/gradle/logging-dependencies.gradle.kts")

plugins {
    jacoco
}

dependencies {
    //Gradle Sub-Project Dependencies
    implementation(project(":implementation:common"))
    testImplementation(project(":implementation:test-support"))
}

subprojects {
    apply(plugin = "jacoco")

    tasks.test {
        finalizedBy(tasks.jacocoTestReport)
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test)
    }

    jacoco {
        toolVersion = "0.8.5"
        reportsDir = file("$buildDir/reports")
    }

    tasks.jacocoTestReport {
        group = "Reporting"
        description = "Generate Jacoco test coverage report"

        reports {
            xml.isEnabled = true
            html.isEnabled = true
            csv.isEnabled = false
        }
    }

    tasks.jacocoTestCoverageVerification  {
        violationRules {
            rule {
                limit {
                    minimum = "0.6".toBigDecimal()
                }
            }
        }
    }
}