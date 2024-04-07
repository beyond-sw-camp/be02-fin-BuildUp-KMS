FROM openjdk:11-jdk-slim-stretch
COPY target/main-service-0.0.1-SNAPSHOT.jar main-service-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "/main-service-0.0.1-SNAPSHOT.jar"]