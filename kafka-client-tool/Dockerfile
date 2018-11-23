FROM openjdk:8-jre
MAINTAINER chinv

# see https://spring.io/guides/gs/spring-boot-docker/
VOLUME /tmp
ADD ./build/libs/kafka-auth-demo-0.0.1-SNAPSHOT.jar app.jar
#COPY ./files/etc/kafka-client-jaas.conf /etc/
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENV KAFKA_USER_NAME_TEST="chinv"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dkafka.user.name=${KAFKA_USER_NAME_TEST} -jar /app.jar" ]
