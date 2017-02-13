FROM java:8
LABEL maintainer "remi.roques@gmail.com"
VOLUME /tmp
ADD build/libs/events-0.1.0-dev.21.uncommitted+gradle-3.3.9af821a.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
