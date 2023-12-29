# Use the official OpenJDK 17 image as a base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/appointment.jar appointment.jar

# Expose port 8081
EXPOSE 8081

# Specify the command to run when the container starts
CMD ["java", "-jar", "appointment.jar"]

