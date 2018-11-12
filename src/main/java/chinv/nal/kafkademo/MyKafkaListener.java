package chinv.nal.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableAutoConfiguration
@EnableBinding(Sink.class)
public class MyKafkaListener
{
	@StreamListener(Sink.INPUT) // destination name 'input.myGroup'
	public void handle(String _msg) {
		log.info(_msg);
	}

//	public void handle(AccountModel _accountModel) {
//		log.info(_accountModel.getAccountId());
//	}
}
