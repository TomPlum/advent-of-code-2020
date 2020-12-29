apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")
apply(from = "$rootDir/gradle/logging-dependencies.gradle.kts")

plugins {
    jacoco
    id("io.gitlab.arturbosch.detekt").version("1.14.2")
}

dependencies {
    //Gradle Sub-Project Dependencies
    implementation(project(":implementation:common"))
    testImplementation(project(":implementation:test-support"))

    testImplementation("io.mockk:mockk:1.10.3")
}

subprojects {
    apply(plugin = "jacoco")
    apply(plugin = "io.gitlab.arturbosch.detekt")

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
                    minimum = "0.9".toBigDecimal()
                }
            }
        }
    }
}

detekt {
    reports {
        html {
            enabled = true
            config = files("$projectDir/src/main/resources/detekt-config.yml")
            destination = file("$buildDir/reports/detekt/report.html")
            baseline = file("$projectDir/src/main/resources/baseline.xml")
            buildUponDefaultConfig = false
            debug = false
            ignoreFailures = false
        }
        xml {
            enabled = false
        }
        txt {
            enabled = false
        }
    }
}
