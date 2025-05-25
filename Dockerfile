# Dockerfile
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY --from=build /app/target/bookclub_app.jar bookclub_app.jar
ENTRYPOINT ["java", "-jar", "/bookclub_app.jar"]
