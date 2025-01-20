FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="movies-service"
LABEL org.opencontainers.image.authors="Vitalii Shapovalov"
LABEL org.opencontainers.image.source=""
LABEL org.opencontainers.image.url=""
LABEL org.opencontainers.image.vendor="Vitalii Shapovalov"
LABEL org.opencontainers.image.version="1.0.0-SNAPSHOT"
LABEL org.opencontainers.image.description="Movies service"
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

EXPOSE 8080

COPY target/apache-kafka-${VERSION}.jar /opt/apache-kafka/apache-kafka.jar

CMD ["java", "-jar", "/opt/apache-kafka/apache-kafka.jar"]