package chinv.nal.kafkademo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class MessageForm implements Serializable
{
	private String topicName;
	private Map<String, String> content;
}
