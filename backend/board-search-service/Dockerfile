FROM openjdk:11-jdk-slim-stretch
COPY target/board-search-service-0.0.1-SNAPSHOT.jar board-search-service-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "/board-search-service-0.0.1-SNAPSHOT.jar"]
