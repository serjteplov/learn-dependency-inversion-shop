plugins {
    id("java")
}

group = "ru.serj.learn.shop"
version = "1.1"

repositories {
    mavenLocal()
    maven {
        url = uri("/home/serg/.m2/repository")
    }
    mavenCentral()
}

dependencies {
    implementation("ru.serj.learn:learn-dependency-inversion-core:1.4")
    implementation("ru.serj.learn:learn-dependency-inversion-application:1.1")
    implementation("ru.serj.learn:learn-dependency-inversion-postgres:1.0")
    implementation("ru.serj.learn:learn-dependency-inversion-telemetry:1.0")
}

tasks.test {
    useJUnitPlatform()
}