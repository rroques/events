FROM java:8
LABEL maintainer "remi.roques@gmail.com"
VOLUME /tmp
ADD events.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
