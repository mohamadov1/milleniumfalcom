# Use the base image with JDK 17 Alpine
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file to the application directory
COPY target/*.jar app.jar

COPY examples/example2/millenium-falcon.json /app/millenium-falcon.json

COPY examples/example2/universe.db /app/universe.db

COPY examples/example2/empire.json /app/empire.json

# Exposez le port 8080 pour permettre l'accès à l'application
EXPOSE 8080

# Run the Java application when the container starts
CMD ["java", "-jar", "/app/app.jar", "millenium-falcon.json"]