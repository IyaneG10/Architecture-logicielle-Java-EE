FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8787

ARG JAR_FILE=./target/Sysco-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} Sysco.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Sysco.jar"]
