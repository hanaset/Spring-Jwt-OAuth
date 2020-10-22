object DependencyVersions {
    const val SWAGGER_VERSION = "2.9.2"
}

dependencies {

    implementation(project(":dobi-common"))

    //swagger
    implementation("io.springfox:springfox-swagger2:${DependencyVersions.SWAGGER_VERSION}")
    implementation("io.springfox:springfox-swagger-ui:${DependencyVersions.SWAGGER_VERSION}")

    // cache
    implementation("org.ehcache:ehcache:3.8.1")
    implementation("org.springframework.boot:spring-boot-starter-cache:2.3.4.RELEASE")
    implementation("javax.cache:cache-api:1.1.1")

    implementation ("org.springframework.boot:spring-boot-starter-security")

    // jsoup
    implementation("org.jsoup:jsoup:1.13.1")

    // JWT Token
    implementation ("io.jsonwebtoken:jjwt:0.9.1")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("com.google.apis:google-api-services-people:v1-rev20201013-1.30.10")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation ("org.springframework.security:spring-security-test")
}

springBoot.buildInfo { properties { } }

configurations {
    val archivesBaseName = "dobi-api-staging"
}