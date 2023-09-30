import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
//    id ("java") // Java 사용하지 않음
    id ("org.springframework.boot") version "3.1.4"
    id ("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("plugin.jpa") version "1.9.0"
}

group = "dev.practice"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations { // 사실 필요없음 롬복을 사용하지 않으므로..
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
//    compileOnly("org.projectlombok:lombok") // 사용하지 않음
//    annotationProcessor ("org.projectlombok:lombok") // 사용하지 않음

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // kotlin 스타일의 직렬화/역직렬화 지원
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> { //코틀린 컴파일러 옵션
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict") // JSR-305
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}