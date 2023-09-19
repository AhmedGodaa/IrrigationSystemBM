FROM amazoncorretto:17-alpine3.16-jdk
WORKDIR /app
EXPOSE 8082
COPY /build/libs/alertservice-0.0.1.jar alertservice-0.0.1.jar
CMD ["java", "-jar", "alertservice-0.0.1.jar"]