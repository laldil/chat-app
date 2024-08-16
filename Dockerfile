FROM maven:3.9.8-amazoncorretto-21-debian-bookworm as BUILD

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package

FROM openjdk:21-jdk

WORKDIR /app

COPY --from=BUILD /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]