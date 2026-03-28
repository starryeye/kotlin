plugins {
    kotlin("jvm") version "2.3.0"
}

group = "dev.starryeye"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // 코루틴 핵심 라이브러리
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    // 코루틴 테스트 (runTest 등)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    // JUnit5
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}