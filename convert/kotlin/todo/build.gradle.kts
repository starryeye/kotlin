import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
//    id ("java") // Java 사용하지 않음
    id ("org.springframework.boot") version "3.1.4"
    id ("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    // 코틀린은 기본적으로 final 클래스라 상속이 불가능함, 따라서 CGLIB 라이브러리를 통한 프록시 생성이 불가능하여 Open 키워드를 붙여준다.
    // 대상: @Component, @Transactional 등
    kotlin("plugin.jpa") version "1.9.0"
    // @Entity 의 경우 no argument constructor 가 필요하다. 이를 자동으로 생성해주는 플러그인
    // 대상: @Entity, @Embeddable, @MappedSuperClass 등
}

group = "dev.practice"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

//configurations { // 사실 필요없음 롬복을 사용하지 않으므로..
//    compileOnly {
//        extendsFrom(configurations.annotationProcessor.get())
//    }
//}

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

    testImplementation("com.ninja-squad:springmockk:3.1.1")
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