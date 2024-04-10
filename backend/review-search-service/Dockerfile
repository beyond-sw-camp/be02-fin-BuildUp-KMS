FROM openjdk:11-jdk-slim-stretch
COPY target/review-search-service-0.0.1-SNAPSHOT.jar review-search-service-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "/review-search-service-0.0.1-SNAPSHOT.jar"]