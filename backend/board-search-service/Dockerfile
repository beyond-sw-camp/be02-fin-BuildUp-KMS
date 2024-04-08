FROM openjdk:11-jdk-slim-stretch
COPY target/board-search-service-0.0.1-SNAPSHOT.jar board-search-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "board.jar"]
CMD ["java", "-jar", "/board-search-service-0.0.1-SNAPSHOT.jar"]
