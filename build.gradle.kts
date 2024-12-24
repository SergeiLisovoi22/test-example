plugins {
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.apache.kafka:kafka-clients:3.7.1")
    implementation("org.apache.kafka:kafka-streams:3.7.1")

    implementation("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")

    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:selenium:1.15.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.4.3")

//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.15.1")
    }
}


tasks.test {
    useJUnitPlatform()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.3.3")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}