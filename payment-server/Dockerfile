# Use a base image with Java and Gradle pre-installed
FROM gradle:latest AS builder

# Set the working directory
WORKDIR /home/gradle/project

# Copy the Gradle build script and settings
COPY build.gradle.kts settings.gradle.kts ./

# Copy the source code
COPY src ./src

# Build the application
RUN gradle clean build -x test

# Use a lightweight base image for the final runtime
FROM amazoncorretto:17.0.7-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Add Pinpoint agent
ADD https://github.com/pinpoint-apm/pinpoint/releases/download/v2.5.3/pinpoint-agent-2.5.3.tar.gz /usr/local
RUN tar -zxvf /usr/local/pinpoint-agent-2.5.3.tar.gz -C /usr/local

# Update Pinpoint agent configuration
RUN sed -i 's/profiler.transport.grpc.collector.ip=127.0.0.1/profiler.transport.grpc.collector.ip=188.24.0.30/g' /usr/local/pinpoint-agent-2.5.3/pinpoint-root.config
RUN sed -i 's/profiler.collector.ip=127.0.0.1/profiler.collector.ip=188.24.0.30/g' /usr/local/pinpoint-agent-2.5.3/pinpoint-root.config

# Expose the port
EXPOSE 8083

# Specify the entry point command
ENTRYPOINT ["java", "-javaagent:/usr/local/pinpoint-agent-2.5.3/pinpoint-bootstrap-2.5.3.jar", "-Dpinpoint.agentId=payment-agent-id", "-Dpinpoint.applicationName=paymentapp", "-Dpinpoint.config=/usr/local/pinpoint-agent-2.5.3/pinpoint-root.config", "-Dspring.profiles.active=release", "-jar", "app.jar"]