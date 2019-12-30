FROM oracle/graalvm-ce:19.3.0-java11

RUN gu install ruby
RUN yum install git -y
RUN git clone https://github.com/sausageRoll/danfe-graal.git
WORKDIR danfe-graal
RUN ./gradlew build bootJar
COPY build/libs/danfe-0.0.1-SNAPSHOT.jar /service.jar

CMD ["java", "-jar", "/service.jar"]
