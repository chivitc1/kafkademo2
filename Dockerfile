FROM openjdk:8-jre
MAINTAINER chinv

# see https://spring.io/guides/gs/spring-boot-docker/
VOLUME /tmp
ADD ./build/libs/kafka-auth-demo-0.0.1-SNAPSHOT.jar app.jar
#COPY ./files/etc/kafka-client-jaas.conf /etc/
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dsasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username=kafka password=kafka-secret; -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
