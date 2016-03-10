FROM java:8

MAINTAINER Tobias Flohre <tobias.flohre@codecentric.de>

ADD target/edmp-sample-stream-source*.jar app.jar

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
