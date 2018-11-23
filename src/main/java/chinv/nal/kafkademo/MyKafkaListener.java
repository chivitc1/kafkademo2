package chinv.nal.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@Slf4j
@EnableBinding(Sink.class)
public class MyKafkaListener
{
	@StreamListener(Sink.INPUT)
	public void handle(String message) {
		log.info("Receive kafka message:" + message);
	}

}
