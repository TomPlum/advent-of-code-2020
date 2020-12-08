dependencies {
    "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.7.0")
    "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    "testImplementation"("org.junit.jupiter:junit-jupiter-params:5.7.0")
    "testImplementation"("org.junit.platform:junit-platform-launcher:1.7.0")
    "testImplementation"("com.willowtreeapps.assertk:assertk-jvm:0.23")
}

tasks.withType<Test> {
    useJUnitPlatform { }
}