FROM graal/ruby:latest

RUN git clone https://github.com/sausageRoll/danfe-graal.git
WORKDIR danfe-graal
RUN ./gradlew build bootJar
COPY build/libs/danfe-0.0.1-SNAPSHOT.jar /service.jar

CMD ["java", "-jar", "/service.jar"]
