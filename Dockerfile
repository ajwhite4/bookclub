# Dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/bookclub_app.jar bookclub_app.jar
ENTRYPOINT ["java", "-jar", "/bookclub_app.jar"]
