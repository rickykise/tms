plugins {
    java
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "ai.fassto"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2022.0.2"

dependencies {
    // Spring
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Log4JDBC
    implementation("org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16")

    // DB
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.0")
    implementation("com.oracle.database.jdbc:ojdbc6:11.2.0.4")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    // 서로다른 DB 사용시 전역 트랜젝션
    implementation("org.springframework.boot:spring-boot-starter-jta-atomikos:2.7.12")


    // Slack
    implementation("net.gpedro.integrations.slack:slack-webhook:1.4.0")

    // SWAGGER
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    // OPEN-FEIGN
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.2")


    // CODEC
    implementation("commons-codec:commons-codec:1.15")

    // LOMBOK
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // GSON
    implementation("com.google.code.gson:gson:2.10.1")

    // 시크릿 매니저
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:3.0.3")
    implementation("org.springframework.cloud:spring-cloud-starter-aws-secrets-manager-config:2.2.6.RELEASE")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
