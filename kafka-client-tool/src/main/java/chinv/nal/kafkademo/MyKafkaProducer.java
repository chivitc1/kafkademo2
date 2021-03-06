package chinv.nal.kafkademo;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaProducer
{
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessageToKafka(String topicName, Message _message)
	{
//		ProducerRecord<String, String> record = new ProducerRecord<>(topicName, messageContent);
		kafkaTemplate.setDefaultTopic(topicName);
		kafkaTemplate.send(_message);
	}
}
