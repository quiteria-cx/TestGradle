
# This Dockerfile will create an image for an Alpine Linux server that hosts a stateless Spring Boot webapp.
# The webapp does not create or modify any files on the host, so no volumes will be required.
# The webapp does output application log messages to STDOUT.

FROM adoptopenjdk/openjdk8:alpine-jre

RUN apk -v update \
   && apk -v upgrade \
   && rm -rf /var/cache/apk/*

# Create group 'appuser', then user 'appuser', and make 'appuser' the default group for this user
RUN addgroup appuser && adduser -G appuser -D appuser

RUN mkdir /var/app

COPY ./build/libs/springboot-helloworld.jar /var/app/my-springboot-app.jar

RUN chown appuser:appuser /var/app/my-springboot-app.jar

EXPOSE 8765

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/var/app/my-springboot-app.jar"]
