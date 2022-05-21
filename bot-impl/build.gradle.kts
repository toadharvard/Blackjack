plugins {
    kotlin("jvm") version "1.6.0"
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
}

group = "me.toadharvard"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation(kotlin("test"))
    implementation(project(":blackjack-base-lib"))
    implementation(project(":bot-lib"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}