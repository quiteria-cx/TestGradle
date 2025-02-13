# gradle-springboot-kotlin
Simple 'Hello World' project that uses Gradle to build a web app developed with Spring Boot, Java and Kotlin

## How to build

From the root project directory:  
* `./gradlew clean build` (build the Spring Boot app)
* `docker image build -t foobar ./app-springboot-helloworld` (build a Docker image that can run the app)

## How to run

From the root project directory after building with Gradle:  
* `java -jar .\app-springboot-helloworld\build\libs\springboot-helloworld.jar`
