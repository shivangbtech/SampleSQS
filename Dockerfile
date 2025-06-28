FROM openjdk:8-jdk-alpine
WORKDIR /app

ARG VERSION
ENV VERSION ${VERSION:-0.0.0-alpha.0}

ARG SERVICE_JAR
ENV SERVICE_JAR ${SERVICE_JAR:-SampleSQS-${VERSION}.jar}

COPY target/${SERVICE_JAR} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
