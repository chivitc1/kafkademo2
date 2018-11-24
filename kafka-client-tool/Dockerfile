FROM openjdk:8-jre
MAINTAINER chinv

# see https://spring.io/guides/gs/spring-boot-docker/
VOLUME /tmp
ADD ./build/libs/kafka-client-tool-0.0.1-SNAPSHOT.jar app.jar
#COPY ./files/etc/kafka-client-jaas.conf /etc/
ENV JAVA_OPTS=""
ENV KAFKA_USERNAME="kafka"
ENV KAFKA_PASSWORD="kafka-password"
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
