FROM amazoncorretto:17
ARG JAR_FILE=target/everest-employee-0.0.3.jar
COPY ${JAR_FILE} employee-service.jar
ENTRYPOINT ["java", "-jar", "/employee-service.jar"]
EXPOSE 8086
