FROM docker.io/openjdk:8-jre

MAINTAINER DSI-DAC-DIF-DS

RUN mkdir /jirakpi /jirakpi/config

COPY target/*.jar /jirakpi/
COPY src/main/resources/application.yml /jirakpi/

WORKDIR /jirakpi/

VOLUME ["/jirakpi/logs"]

CMD java -jar *.jar --spring.config.location=application.yml