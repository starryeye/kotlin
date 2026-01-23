plugins {
    kotlin("jvm") version "1.9.0"
    java // java 파일도 가능하도록..
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

java { // java 파일도 가능하도록..
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}