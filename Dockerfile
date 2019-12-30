FROM graal/ruby:latest

COPY build/libs/danfe-0.0.1-SNAPSHOT.jar /service.jar

CMD ["java", "-jar", "/service.jar"]
