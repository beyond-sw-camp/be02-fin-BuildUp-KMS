FROM openjdk:11-jdk-slim-stretch
COPY target/gateway-0.0.1-SNAPSHOT.jar gateway-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "/gateway-0.0.1-SNAPSHOT.jar"]