plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"

    // 엔티티 관련 어노테이션이 붙은 클래스에 자동으로 기본 생성자를 만들어준다.
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("jvm") version "1.9.24"

    // 내부적으로 다음 어노테이션에 all-open이 적용되어 있다.
    // @Component
    // @Async
    // @Transactional
    // @Cacheable
    // @SpringBootTest
    kotlin("plugin.spring") version "1.9.24"

    // allopen plugin for jpa
    kotlin("plugin.allopen") version "1.9.24"

    // kotlin annotation processing tool
    kotlin("kapt") version "1.9.24"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // 기존 jackson deserialize 시 기본 생성자가 필요한데, data class에서 단일 생성자로도 deserialize할 수 있게 해주는 모듈
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // kotlin reflection library. 코틀린에서는 런타임 라이브러리 용량을 줄이기 위해 기본적으로 reflect를 제공하지 않는다...
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")

    kapt("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}

kotlin {
    compilerOptions {
        // for null safety
        // https://kotlinlang.org/docs/java-interop.html#jsr-305-support
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}
