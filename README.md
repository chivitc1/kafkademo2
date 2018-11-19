# Demo kafka authentication using SASL_PLAINTEXT protocol
- SASL_PLAINTEXT protocol Should be used in internal network only
- For public internet, consider use SSL, TLS

## Setup

### Requirement for kafka authen
Kafka server version 0.10.2.2+

Kafka client lib version 0.9+

### Connection to zookeeper

It is optional to config JAAS for Zookeeper


### Gradle dependency for Kafka client 0.9+ config

compile group: 'org.springframework.cloud', name: 'spring-cloud-starter-stream-kafka', version: '1.3.3.RELEASE'

### Setup for Kafka Broker

#### Authen config file for server
KafkaServer {
    org.apache.kafka.common.security.plain.PlainLoginModule required
    username="admin"
    password="kafka-secret"
    user_admin="kafka-secret"
    user_kafka="kafka-secret";
};
KafkaClient {
    org.apache.kafka.common.security.plain.PlainLoginModule required
    username="kafka"
    password="kafka-secret";
};

#### Authen config file for server
KafkaClient {
    org.apache.kafka.common.security.plain.PlainLoginModule required
    username="kafka"
    password="kafka-secret";
};

#### For Kafka server.properties file

sasl.enabled.mechanisms=PLAIN

sasl.mechanism.inter.broker.protocol=PLAIN

security.inter.broker.protocol=SASL_PLAINTEXT

listeners=SASL_PLAINTEXT://localhost:9092

#### For Kafka running in docker-compose

        environment:
            KAFKA_LISTENERS: "SASL_PLAINTEXT://:9092"
            KAFKA_ADVERTISED_LISTENERS: "SASL_PLAINTEXT://${HOST_IP}:9092"
            KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: "SASL_PLAINTEXT:SASL_PLAINTEXT"
            KAFKA_INTER_BROKER_LISTENER_NAME: "SASL_PLAINTEXT"
            KAFKA_SASL_MECHANISM_INTER_BROKER_PROTOCOL: "PLAIN"
            KAFKA_SECURITY_PROTOCOL: "SASL_PLAINTEXT"
            KAFKA_SASL_ENABLED_MECHANISMS: "PLAIN"
            KAFKA_OPTS: "-Djava.security.auth.login.config=/opt/kafka/config/kafka-server-jaas.conf"

### Setup for Kafka client

- Note: auth require kafka client version 0.9+

Here Spring Cloud Stream version which use Kafka client 0.10.1.1

compile group: 'org.springframework.cloud', name: 'spring-cloud-starter-stream-kafka', version: '1.3.3.RELEASE'

- Set env for Spring cloud stream variables:

spring.cloud.stream.kafka.binder.jaas.loginModule=org.apache.kafka.common.security.plain.PlainLoginModule

spring.cloud.stream.kafka.binder.configuration.security.protocol=SASL_PLAINTEXT

spring.cloud.stream.kafka.binder.configuration.sasl.mechanism = PLAIN

#### Setup for Kafka producer/consumer console

producer.properties / consumer.properties:

security.protocol=SASL_PLAINTEXT

sasl.mechanism=PLAIN

#### Set JVM $JAVA_OPTS for jar run

java -Djava.security.auth.login.config=/etc/kafka-client-jaas.conf -jar /app.jar


Or dockerfile:

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.auth.login.config=/etc/kafka-client-jaas.conf -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]


## Run using docker-compose
Build:

$ ./gradlew clean build assemble

Run docker-compose:

$ docker-compose up -d --build

Call api to test sending message:

curl -H "Content-Type: application/json" -X POST -d '{"testdata":"Hello everybody"}' http://localhost:8080/kafka/testtopicname

## Run using command line

$ export KAFKA_OPTS=-Djava.security.auth.login.config=config/broker_jaas.conf
$ bin/zookeeper-server-start.sh config/zookeeper.properties

$ export KAFKA_OPTS=-Djava.security.auth.login.config=config/broker_jaas.conf
$ bin/kafka-server-start.sh config/server1.properties

$ export KAFKA_OPTS=-Djava.security.auth.login.config=config/producer_jaas.conf
$ bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning --consumer.config config/consumer.properties

## Spring boot Spring Cloud Stream Kafka authen using JAAS

spring.cloud.stream.kafka.binder.brokers=localhost:9092,localhost:9093,localhost:9094
spring.cloud.stream.kafka.binder.zkNodes=localhost:2181

spring.cloud.stream.bindings.output.destination=test2
spring.cloud.stream.bindings.output.content-type=application/json

spring.cloud.stream.bindings.input.destination=test2

spring.cloud.stream.kafka.binder.jaas.loginModule=org.apache.kafka.common.security.plain.PlainLoginModule
spring.cloud.stream.kafka.binder.jaas.options.username=kafka
spring.cloud.stream.kafka.binder.jaas.options.password=kafka-secret
spring.cloud.stream.kafka.binder.configuration.security.protocol=SASL_PLAINTEXT
spring.cloud.stream.kafka.binder.configuration.sasl.mechanism = PLAIN

(No need JAAS config file)