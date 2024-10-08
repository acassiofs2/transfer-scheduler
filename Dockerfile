FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY ./infrastructure/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]