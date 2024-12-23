FROM nginx:1.23.3

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

ENV VERSION="1.0.0-SNAPSHOT"

ENV API_URL http://movies-gateway:8080/api

EXPOSE 80

ADD dist/frontend/browser /usr/share/nginx/html/
ADD docker/etc/nginx/templates/ /etc/nginx/templates/
