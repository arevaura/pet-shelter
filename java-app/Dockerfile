FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY openapi.yaml .
COPY java-app/ .
# Generate Java code and build the JAR:
RUN mvn clean package -DskipTests 

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Run the app:
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]