################################################
#             JAVA MICROSERVICE IMAGE          #
################################################
FROM bellsoft/liberica-openjdk-alpine:17

ARG JAR_FILE=config-service/build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","/application.jar"]