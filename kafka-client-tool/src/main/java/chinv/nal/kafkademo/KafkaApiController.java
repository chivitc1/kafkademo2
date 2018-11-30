package chinv.nal.kafkademo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
public class KafkaApiController
{
	@Autowired
	private MyKafkaProducer myKafkaProducer;

	@GetMapping("/kafka/test")
	public ResponseEntity<String> testGet(){
		return new ResponseEntity<>("I got your requet", HttpStatus.OK);
	}

	@RequestMapping(path = "/kafka", method = POST, consumes = "*/*")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void handleRequest(@RequestBody MessageForm _messageForm,
							  @RequestHeader(HttpHeaders.CONTENT_TYPE) Object contentType) throws JsonProcessingException
	{
		log.info("Request in: ");

		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(_messageForm.getContent());
		Message<String> message = MessageBuilder.createMessage(jsonResult,
				new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, contentType)));

		myKafkaProducer.sendMessageToKafka(_messageForm.getTopicName(), message);
		log.info("Request out.");
	}

}
