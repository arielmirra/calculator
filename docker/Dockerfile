FROM openjdk:8
WORKDIR /app
COPY .env .
COPY calculator-backend-0.0.1-SNAPSHOT.jar .
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "calculator-backend-0.0.1-SNAPSHOT.jar"]
