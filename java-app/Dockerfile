FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY java-app/pom.xml .
COPY java-app/.mvn/ .mvn/
COPY java-app/mvnw .
# Fix line endings and permissions for mvnw script
RUN dos2unix mvnw || sed -i 's/\r$//' mvnw && chmod +x mvnw
# Clean up any conflicting .m2 directory
RUN rm -rf /root/.m2
# Verify mvnw exists and is executable
RUN ls -la mvnw && ./mvnw --version
# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline -B
# Copy source code
COPY java-app/src ./src
COPY openapi.yaml .
# Build the application
RUN ./mvnw clean package -DskipTests -B

FROM eclipse-temurin:11-jre-jammy
WORKDIR /app
# Add a non-root user for security
RUN addgroup --system app && adduser --system app --ingroup app
USER app:app
COPY --from=build /app/target/*.jar app.jar
# Health check and run optimizations:
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 CMD curl -f http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]