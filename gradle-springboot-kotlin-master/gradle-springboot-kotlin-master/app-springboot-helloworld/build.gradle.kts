
// I chose to use the 'build.gradle.kts' file for this subproject,
// rather than put its subproject-specific config in the root project
// buildfile as I did for the other subprojects.

plugins {
    id("org.springframework.boot")
    id("application")
}

apply {
    plugin("io.spring.dependency-management")
}

application {
    this.mainClassName = "com.jimtough.gsk.app.springboot.HelloWorldSpringBootApp"
}

// Force the BootJar task to generate a JAR with the name: "springboot-helloworld.jar"
// I don't want the project name or version used, as it would have by default.
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveBaseName.set("springboot-helloworld")
    archiveVersion.set("")
}

dependencies {
    // Delcare the 'hello factory' and 'world factory' sibling (library) projects as dependencies
    implementation(project(":library-hello-factory"))
    implementation(project(":library-world-factory"))

    // Add BOM for AWS Java SDK, then individual SDK modules
    implementation(platform("software.amazon.awssdk:bom:2.13.54"))
    implementation("software.amazon.awssdk:secretsmanager")

    // Declare other regular dependencies that come from Maven Central (or whatever repo you declared in root project)
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // Added Thymeleaf while learning to use Spring MVC 'ErrorController' class
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // Needed for JSON marshalling/unmarshalling
    implementation("com.fasterxml.jackson.core:jackson-databind")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
