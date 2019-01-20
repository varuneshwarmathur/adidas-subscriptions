FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8100

ARG JAR_FILE=target/email-service-1.0-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]