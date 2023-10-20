FROM openjdk:8-jdk-alpine

RUN apk --no-cache update && \
    apk --no-cache upgrade && \
    apk add --update tzdata && \
    rm -rf /var/cache/apk/*
    
RUN echo ${TZ} > /etc/timezone
    
EXPOSE 80

ADD /target/Projetofinal-api-0.0.1-SNAPSHOT.jar Projetofinal-api.jar

ENTRYPOINT ["java","-jar","Projetofinal-api.jar", "--server.port=80"]