import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.4"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10" apply false
    kotlin("plugin.jpa") version "1.7.10" apply false
    kotlin("plugin.allopen") version "1.7.10"
    kotlin("plugin.noarg") version "1.7.10"
}

version = "unspecified"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.slf4j:slf4j-api:1.7.30")

    api("com.fasterxml.jackson.core:jackson-databind")
    api("com.fasterxml.jackson.module:jackson-module-kotlin")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    api("io.github.microutils:kotlin-logging-jvm:2.1.23")

    implementation("io.github.openfeign:feign-core:10.12")
    implementation("io.github.openfeign:feign-jackson:10.12")

    implementation("org.hibernate:hibernate-envers")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j")

    implementation("com.github.database-rider:rider-core:1.32.1")
    implementation("com.github.database-rider:rider-spring:1.32.1")

    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.5.4")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2020.0.3")
    }
}

apply {
    plugin("idea")
    plugin("java-library")
    plugin("kotlin")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
    plugin("org.jetbrains.kotlin.plugin.spring")
    plugin("org.jetbrains.kotlin.plugin.allopen")
    plugin("org.jetbrains.kotlin.plugin.noarg")
    plugin("org.jetbrains.kotlin.plugin.jpa")
    plugin("org.jetbrains.kotlin.kapt")
    plugin("jacoco")
    plugin("java-test-fixtures")
}

allOpen {
    annotation("javax.persistence.Entity")
}

noArg {
    annotation("javax.persistence.Entity")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
