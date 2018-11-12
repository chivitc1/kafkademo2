package chinv.nal.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@EnableBinding
@RestController
@Slf4j
public class KafkaApiController
{
	@Autowired
	private BinderAwareChannelResolver resolver;


	@GetMapping("/kafka/test")
	public ResponseEntity<String> testGet(){
		return new ResponseEntity<>("I got your requet", HttpStatus.OK);
	}

	@RequestMapping(path = "/kafka/{target}", method = POST, consumes = "*/*")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void handleRequest(@RequestBody String body, @PathVariable("target") String target,
							  @RequestHeader(HttpHeaders.CONTENT_TYPE) Object contentType) {
		log.info("Request in");
		sendMessage(body, target, contentType);
		log.info("Request out");
	}

	private void sendMessage(String body, String target, Object contentType) {
		resolver.resolveDestination(target).send(MessageBuilder.createMessage(body,
				new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, contentType))));
	}
}
