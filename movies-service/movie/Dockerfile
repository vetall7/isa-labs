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

#ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:8081/movies
#ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.postgresql.Driver
#ENV SPRING_DATASOURCE_USERNAME=admin-movies
#ENV SPRING_DATASOURCE_PASSWORD=admin
#
#ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
#ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
#ENV SPRING_JPA_GENERATE_DDL=true
#ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

EXPOSE 8080

COPY target/movie-${VERSION}.jar /opt/movie/movie.jar

CMD ["java", "-jar", "/opt/movie/movie.jar"]