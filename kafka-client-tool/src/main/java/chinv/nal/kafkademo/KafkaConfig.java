package chinv.nal.kafkademo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig
{
	@Autowired
	AppProperties props;

	@Bean
	public ProducerFactory<String, String> producerFactory()
	{
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public Map<String, Object> producerConfigs()
	{
		Map<String, Object> producerProps = new HashMap<>();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getKafkaServer());
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonSerializer.class.getName());
		producerProps.put("sasl.jaas.config", props.getSaslJaasConfig());
		producerProps.put("sasl.mechanism", props.getSaslMechanism());
		producerProps.put("security.protocol", props.getSecurityProtocol());
		return producerProps;
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate()
	{
		return new KafkaTemplate<>(producerFactory());
	}
}
