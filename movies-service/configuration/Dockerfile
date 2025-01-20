FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="configuration-service"
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

ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8085/eureka
ENV EUREKA_INSTANCE_INSTANCE_ID=1

ENV SPRING_PROFILES_ACTIVE=native

ENV SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCH_LOCATIONS=file:/var/configuration/configuration

EXPOSE 8080

COPY target/configuration-${VERSION}.jar /opt/configuration/configuration.jar

CMD ["java", "-jar", "/opt/configuration/configuration.jar"]