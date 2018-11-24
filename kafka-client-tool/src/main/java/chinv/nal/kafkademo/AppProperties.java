package chinv.nal.kafkademo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties
public class AppProperties
{
	@Value("${kafka.binder.brokers}")
	private String kafkaServer;

	@Value("${security.protocol}")
	private String securityProtocol;

	@Value("${sasl.mechanism}")
	private String saslMechanism;

	@Value("${sasl.jaas.config}")
	private String saslJaasConfig;
}
