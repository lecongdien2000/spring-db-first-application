FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar spring-db-first-application-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/spring-db-first-application-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080