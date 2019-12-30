FROM openjdk:11.0.5

COPY build/libs/danfe-0.0.1-SNAPSHOT.jar /service.jar

CMD ["java", "-jar", "/service.jar"]
